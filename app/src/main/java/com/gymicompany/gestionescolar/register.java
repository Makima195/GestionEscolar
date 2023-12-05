package com.gymicompany.gestionescolar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.hivemq.client.mqtt.MqttClient;
import com.hivemq.client.mqtt.mqtt3.Mqtt3AsyncClient;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;


public class register extends AppCompatActivity {

    Button btn_register;
    EditText name, email, password, perfil, anuncio_general, aviso;
    FirebaseFirestore mFirestore;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mFirestore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();


        name = findViewById(R.id.nombre);
        email = findViewById(R.id.correo);
        password = findViewById(R.id.contrasena);
        btn_register = findViewById(R.id.btn_registro);
        perfil = findViewById(R.id.perfil);
        anuncio_general = findViewById(R.id.anuncio_general);
        aviso = findViewById(R.id.aviso);


        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameUser = name.getText().toString().trim();
                String emailUser = email.getText().toString().trim();
                String passUser = password.getText().toString().trim();
                String perfilUser = perfil.getText().toString().trim();
                String anuncioUser = anuncio_general.getText().toString().trim();
                String avisoUser = aviso.getText().toString().trim();

                if(nameUser.isEmpty() && emailUser.isEmpty() && passUser.isEmpty()){
                    Toast.makeText(register.this, "Complete los datos", Toast.LENGTH_SHORT).show();
                }else{
                    registerUser(nameUser,emailUser,passUser,perfilUser,anuncioUser,avisoUser);
                }

            }
        });
    }

    private void registerUser(String nameUser, String emailUser, String passUser, String perfilUser, String anuncioUser, String avisoUser) {
        mAuth.createUserWithEmailAndPassword(emailUser, passUser).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                String id = mAuth.getCurrentUser().getUid();

                Map<String, Object> map = new HashMap<>();
                map.put("id", id);
                map.put("name", nameUser);
                map.put("email", emailUser);
                map.put("password", passUser);
                map.put("perfil", perfilUser);
                map.put("anuncio_general", anuncioUser);
                map.put("aviso",avisoUser);

                mFirestore.collection("user").document(id).set(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        finish();
                        startActivity(new Intent(register.this, ReceptionActivity.class));
                        Toast.makeText(register.this, "Usuario registrado con éxito", Toast.LENGTH_SHORT).show();
                        iniciarConexionMQTT(String.valueOf(name));
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(register.this, "Error al guardar", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(register.this, "Error al registrar", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void iniciarConexionMQTT(String name) {
        String clusterUrl = "8fd1d93460644ebd86917bebafc7953c.s2.eu.hivemq.cloud";
        int port = 8883;
        String username = "Sarpefh";
        String password = "Hairo015?";
        String clientId = name + System.currentTimeMillis();
        Log.e("Client ID: ", clientId);

        Mqtt3AsyncClient client = MqttClient.builder()
                .useMqttVersion3()
                .identifier(clientId)
                .serverHost(clusterUrl)
                .serverPort(port)
                .useSslWithDefaultConfig()
                .buildAsync();

        client.connectWith()
                .simpleAuth()
                .username(username)
                .password(password.getBytes())
                .applySimpleAuth()
                .send()
                .whenComplete((connAck, throwable) -> {
                    if (throwable != null) {
                        Log.v("MQTT","ERROR CONCHETUMARE");
                    } else {
                        // Configurar suscripciones o iniciar la publicación
                        suscribirATopicoUsuarios(name, client);
                        publicarMensajeATopico(name, client, "He registrado con exito!");
                    }
                });

    }

    private void suscribirATopicoUsuarios(String name, Mqtt3AsyncClient client) {
        String topicUsuarios = "RegisterUsuarios";

        client.subscribeWith()
                .topicFilter(topicUsuarios)
                .callback(publish -> {
                    // Manejar los mensajes recibidos en el tópico de usuarios
                    byte[] payload = publish.getPayloadAsBytes();
                    String mensajeRecibido = new String(payload);
                    Log.e("Mensaje recibido", mensajeRecibido);
                })
                .send()
                .whenComplete((subAck, throwable) -> {
                    if (throwable != null) {
                        // Manejar el fallo de suscripción MQTT
                    } else {
                        // Manejar la suscripción exitosa
                    }
                });
    }


    private void publicarMensajeATopico(String nombre, Mqtt3AsyncClient client, String mensaje) {
        String topicUsuario = "RegisterUsuarios";

        client.publishWith()
                .topic(topicUsuario)
                .payload(mensaje.getBytes())
                .send()
                .whenComplete((publish, throwable) -> {
                    if (throwable != null) {
                        // Manejar el fallo de publicación MQTT
                        Log.e("Fallo de publicación", throwable.getMessage());
                    } else {
                        // Manejar la publicación exitosa, por ejemplo, registro o métricas
                        Log.e("Publicación exitosa", "He ingresado en el topic! " + topicUsuario);
                    }
                });
    }





}