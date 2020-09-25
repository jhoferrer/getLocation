package com.example.delivery_mypao;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    User[] usuario_lista_quadrante1 = new User[400];
    User[] usuario_lista_quadrante2 = new User[400];
    User[] usuario_lista_quadrante3 = new User[400];
    User[] usuario_lista_quadrante4 = new User[400];
    User[] usuario_lista_quadrante5 = new User[400];
    User[] usuario_lista_quadrante6 = new User[400];
    Integer[] ind_usuarios_horarios = new Integer[200];
    Boolean bit_primeiro_lista = false;

    ArrayList <String> lista_usuarios1 = new ArrayList<>();
    ArrayList <String> lista_usuarios2 = new ArrayList<>();
    ArrayList <String> lista_usuarios3 = new ArrayList<>();
    ArrayList <String> lista_usuarios4 = new ArrayList<>();
    ArrayList <String> lista_usuarios5 = new ArrayList<>();
    ArrayList <String> lista_usuarios6 = new ArrayList<>();
    ArrayList <String> lista_usuarios_horarios = new ArrayList<>();
    ArrayAdapter<String> adaptador_user,adaptador_user_horario;
    ListView listusuarios;
    User usuario = new User();
    dados_app dados = new dados_app();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myref = database.getReference("Users");
    Integer a=0,b=0,c=0,d=0,e=0,f=0,quadrante=0,total_paes=0,total_queijo=0,total_presunto=0,total_manteiga=0,total_entregas=0,entregas_feitas=0,total_paes_entregue=0,total_queijo_entregue=0,
            total_presunto_entregue=0,total_manteiga_entregue=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button botao_baixa_lista = (Button)findViewById(R.id.botao_baixa_lista);
        final Button botao_quadrante1 = (Button)findViewById(R.id.button_quadrante1);
        final Button botao_quadrante2 = (Button)findViewById(R.id.button_quadrante2);
        final Button botao_quadrante3 = (Button)findViewById(R.id.button_quadrante3);
        final Button botao_quadrante4 = (Button)findViewById(R.id.button_quadrante4);
        final Button botao_quadrante5 = (Button)findViewById(R.id.button_quadrante5);
        final Button botao_quadrante6 = (Button)findViewById(R.id.button_quadrante6);
        final Button botao_horario1 = (Button)findViewById(R.id.button_horario1);
        final Button botao_horario2 = (Button)findViewById(R.id.button_horario2);
        final Button botao_horario3 = (Button)findViewById(R.id.button_horario3);
        final Button botao_horario4 = (Button)findViewById(R.id.button_horario4);
        final Button botao_inicia_entrega = (Button)findViewById(R.id.botao_inicia_entregas);
        final TextView view_usuarios = (TextView)findViewById(R.id.View_users);
        final TextView textView_quant_pao = findViewById(R.id.textView_quant_pao);
        final TextView textView_quant_pendente_pao = findViewById(R.id.textView_quant_pendente_pao);
        final TextView textView_quant_queijo = findViewById(R.id.textView_quant_queijo);
        final TextView textView_quant_pendente_queijo = findViewById(R.id.textView_quant_pendente_queijo);
        final TextView textView_quant_presunto = findViewById(R.id.textView_quant_presunto);
        final TextView textView_quant_pendente_presunto = findViewById(R.id.textView_quant_pendente_presunto);
        final TextView textView_quant_manteiga = findViewById(R.id.textView_quant_manteiga);
        final TextView textView_quant_pendente_manteiga = findViewById(R.id.textView_quant_pendente_manteiga);
        final TextView textView_total_entregas = findViewById(R.id.textView_total_entregas);
        final TextView textView_total_entregas_feitas = findViewById(R.id.textView_total_entregas_feitas);
        listusuarios = (ListView)findViewById(R.id.listview_users);

        botao_quadrante1.setVisibility(View.INVISIBLE);
        botao_quadrante2.setVisibility(View.INVISIBLE);
        botao_quadrante3.setVisibility(View.INVISIBLE);
        botao_quadrante4.setVisibility(View.INVISIBLE);
        botao_quadrante5.setVisibility(View.INVISIBLE);
        botao_quadrante6.setVisibility(View.INVISIBLE);
        botao_horario1.setVisibility(View.INVISIBLE);
        botao_horario2.setVisibility(View.INVISIBLE);
        botao_horario3.setVisibility(View.INVISIBLE);
        botao_horario4.setVisibility(View.INVISIBLE);
        botao_inicia_entrega.setEnabled(false);

        botao_inicia_entrega.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (quadrante) {
                    case 1:
                        myref.child("usuarios").child(usuario_lista_quadrante1[ind_usuarios_horarios[0]].phone).child("pedido").setValue("quase");
                        botao_inicia_entrega.setEnabled(false);
                        break;
                    case 2:
                        myref.child("usuarios").child(usuario_lista_quadrante2[ind_usuarios_horarios[0]].phone).child("pedido").setValue("quase");
                        botao_inicia_entrega.setEnabled(false);
                        break;
                    case 3:
                        myref.child("usuarios").child(usuario_lista_quadrante3[ind_usuarios_horarios[0]].phone).child("pedido").setValue("quase");
                        botao_inicia_entrega.setEnabled(false);
                        break;
                    case 4:
                        myref.child("usuarios").child(usuario_lista_quadrante4[ind_usuarios_horarios[0]].phone).child("pedido").setValue("quase");
                        botao_inicia_entrega.setEnabled(false);
                        break;
                    case 5:
                        myref.child("usuarios").child(usuario_lista_quadrante5[ind_usuarios_horarios[0]].phone).child("pedido").setValue("quase");
                        botao_inicia_entrega.setEnabled(false);
                        break;
                    case 6:
                        myref.child("usuarios").child(usuario_lista_quadrante6[ind_usuarios_horarios[0]].phone).child("pedido").setValue("quase");
                        botao_inicia_entrega.setEnabled(false);
                        break;
                }
            }
        });

        botao_baixa_lista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lista_usuarios1.clear();
                lista_usuarios2.clear();
                lista_usuarios3.clear();
                lista_usuarios4.clear();
                lista_usuarios5.clear();
                lista_usuarios6.clear();
                botao_quadrante1.setVisibility(View.VISIBLE);
                botao_quadrante2.setVisibility(View.VISIBLE);
                botao_quadrante3.setVisibility(View.VISIBLE);
                botao_quadrante4.setVisibility(View.VISIBLE);
                botao_quadrante5.setVisibility(View.VISIBLE);
                botao_quadrante6.setVisibility(View.VISIBLE);
                botao_horario1.setVisibility(View.VISIBLE);
                botao_horario2.setVisibility(View.VISIBLE);
                botao_horario3.setVisibility(View.VISIBLE);
                botao_horario4.setVisibility(View.VISIBLE);
                listusuarios.clearChoices();
                a=0;b=0;c=0;d=0;e=0;f=0;quadrante=0;

                Query get_list = myref.child("usuarios").orderByChild("pedido").equalTo("true");
                get_list.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Log.e("Count " ,""+dataSnapshot.getChildrenCount());
                        textView_total_entregas.setText("Total de entregas: "+dataSnapshot.getChildrenCount());
                        textView_total_entregas_feitas.setText("Entregas feitas: 0");
                        for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                            usuario = snapshot.getValue(User.class);
                            switch (usuario.quadrante) {
                                case 1:
                                    usuario_lista_quadrante1[a] = usuario;
                                    lista_usuarios1.add(usuario_lista_quadrante1[a].name + ", endereço:" + usuario_lista_quadrante1[a].endereco + ", referencia" + usuario_lista_quadrante1[a].referencia + ", horário entrega:" + usuario_lista_quadrante1[a].horario_entrega
                                            + ", quantidade:\nPães: " + usuario_lista_quadrante1[a].quantpaes+"\nQueijo: "+usuario_lista_quadrante1[a].quantqueijo+"\nPresunto: "+usuario_lista_quadrante1[a].quantpresunto
                                            +"\nManteiga: "+usuario_lista_quadrante1[a].quantmanteiga);
                                    a++;
                                    break;
                                case 2:
                                    usuario_lista_quadrante2[b] = usuario;
                                    lista_usuarios2.add(usuario_lista_quadrante2[b].name + ", endereço:" + usuario_lista_quadrante2[b].endereco + ", referencia" + usuario_lista_quadrante2[b].referencia + ", horário entrega:" + usuario_lista_quadrante2[b].horario_entrega
                                            + ", quantidade:\nPães: " + usuario_lista_quadrante2[b].quantpaes+"\nQueijo: "+usuario_lista_quadrante2[b].quantqueijo+"\nPresunto: "+usuario_lista_quadrante2[b].quantpresunto
                                            +"\nManteiga: "+usuario_lista_quadrante2[b].quantmanteiga);
                                    b++;
                                    break;
                                case 3:
                                    usuario_lista_quadrante3[c] = usuario;
                                    lista_usuarios3.add(usuario_lista_quadrante3[c].name + ", endereço:" + usuario_lista_quadrante3[c].endereco + ", referencia" + usuario_lista_quadrante3[c].referencia + ", horário entrega:" + usuario_lista_quadrante3[c].horario_entrega
                                            + ", quantidade:\nPães: " + usuario_lista_quadrante3[c].quantpaes+"\nQueijo: "+usuario_lista_quadrante3[c].quantqueijo+"\nPresunto: "+usuario_lista_quadrante3[c].quantpresunto
                                            +"\nManteiga: "+usuario_lista_quadrante3[c].quantmanteiga);
                                    c++;
                                    break;
                                case 4:
                                    usuario_lista_quadrante4[d] = usuario;
                                    lista_usuarios4.add(usuario_lista_quadrante4[d].name + ", endereço:" + usuario_lista_quadrante4[d].endereco + ", referencia" + usuario_lista_quadrante4[d].referencia + ", horário entrega:" + usuario_lista_quadrante4[d].horario_entrega
                                            + ", quantidade:\nPães: " + usuario_lista_quadrante4[d].quantpaes+"\nQueijo: "+usuario_lista_quadrante4[d].quantqueijo+"\nPresunto: "+usuario_lista_quadrante4[d].quantpresunto
                                            +"\nManteiga: "+usuario_lista_quadrante4[d].quantmanteiga);
                                    d++;
                                    break;
                                case 5:
                                    usuario_lista_quadrante5[e] = usuario;
                                    lista_usuarios5.add(usuario_lista_quadrante5[e].name + ", endereço:" + usuario_lista_quadrante5[e].endereco + ", referencia" + usuario_lista_quadrante5[e].referencia + ", horário entrega:" + usuario_lista_quadrante5[e].horario_entrega
                                            + ", quantidade:\nPães: " + usuario_lista_quadrante5[e].quantpaes+"\nQueijo: "+usuario_lista_quadrante5[e].quantqueijo+"\nPresunto: "+usuario_lista_quadrante5[e].quantpresunto
                                            +"\nManteiga: "+usuario_lista_quadrante5[e].quantmanteiga);
                                    e++;
                                    break;
                                case 6:
                                    usuario_lista_quadrante6[f] = usuario;
                                    lista_usuarios6.add(usuario_lista_quadrante6[f].name + ", endereço:" + usuario_lista_quadrante6[f].endereco + ", referencia" + usuario_lista_quadrante6[f].referencia + ", horário entrega:" + usuario_lista_quadrante6[f].horario_entrega
                                            + ", quantidade:\nPães: " + usuario_lista_quadrante6[f].quantpaes+"\nQueijo: "+usuario_lista_quadrante6[f].quantqueijo+"\nPresunto: "+usuario_lista_quadrante6[f].quantpresunto
                                    +"\nManteiga: "+usuario_lista_quadrante6[f].quantmanteiga);
                                    f++;
                                    break;
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                Query get_dados_app = myref.child("Servidor").orderByChild("name").equalTo("dados_app");
                get_dados_app.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot data : dataSnapshot.getChildren())
                            dados = data.getValue(dados_app.class);
                            textView_quant_pao.setText(String.valueOf(dados.quant_disponivel_pao));
                            textView_quant_queijo.setText(String.valueOf(dados.quant_disponivel_queijo));
                            textView_quant_presunto.setText(String.valueOf(dados.quant_disponivel_presunto));
                            textView_quant_manteiga.setText(String.valueOf(dados.quant_disponivel_manteiga));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        botao_quadrante1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adaptador_user = new ArrayAdapter<String>(getApplicationContext(),R.layout.lista,lista_usuarios1);
                listusuarios.setAdapter(adaptador_user);
                listusuarios.setVisibility(View.VISIBLE);
                quadrante =1;
                botao_quadrante1.setBackgroundColor(Color.YELLOW);
                botao_quadrante2.setBackgroundColor(Color.LTGRAY);
                botao_quadrante3.setBackgroundColor(Color.LTGRAY);
                botao_quadrante4.setBackgroundColor(Color.LTGRAY);
                botao_quadrante5.setBackgroundColor(Color.LTGRAY);
                botao_quadrante6.setBackgroundColor(Color.LTGRAY);
                botao_horario1.setBackgroundColor(Color.LTGRAY);
                botao_horario2.setBackgroundColor(Color.LTGRAY);
                botao_horario3.setBackgroundColor(Color.LTGRAY);
                botao_horario4.setBackgroundColor(Color.LTGRAY);
            }
        });

        botao_quadrante2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adaptador_user = new ArrayAdapter<String>(getApplicationContext(),R.layout.lista,lista_usuarios2);
                listusuarios.setAdapter(adaptador_user);
                listusuarios.setVisibility(View.VISIBLE);
                quadrante =2;
                botao_quadrante1.setBackgroundColor(Color.LTGRAY);
                botao_quadrante2.setBackgroundColor(Color.YELLOW);
                botao_quadrante3.setBackgroundColor(Color.LTGRAY);
                botao_quadrante4.setBackgroundColor(Color.LTGRAY);
                botao_quadrante5.setBackgroundColor(Color.LTGRAY);
                botao_quadrante6.setBackgroundColor(Color.LTGRAY);
                botao_horario1.setBackgroundColor(Color.LTGRAY);
                botao_horario2.setBackgroundColor(Color.LTGRAY);
                botao_horario3.setBackgroundColor(Color.LTGRAY);
                botao_horario4.setBackgroundColor(Color.LTGRAY);
            }
        });

        botao_quadrante3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adaptador_user = new ArrayAdapter<String>(getApplicationContext(),R.layout.lista,lista_usuarios3);
                listusuarios.setAdapter(adaptador_user);
                listusuarios.setVisibility(View.VISIBLE);
                quadrante =3;
                botao_quadrante1.setBackgroundColor(Color.LTGRAY);
                botao_quadrante2.setBackgroundColor(Color.LTGRAY);
                botao_quadrante3.setBackgroundColor(Color.YELLOW);
                botao_quadrante4.setBackgroundColor(Color.LTGRAY);
                botao_quadrante5.setBackgroundColor(Color.LTGRAY);
                botao_quadrante6.setBackgroundColor(Color.LTGRAY);
                botao_horario1.setBackgroundColor(Color.LTGRAY);
                botao_horario2.setBackgroundColor(Color.LTGRAY);
                botao_horario3.setBackgroundColor(Color.LTGRAY);
                botao_horario4.setBackgroundColor(Color.LTGRAY);
            }
        });

        botao_quadrante4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adaptador_user = new ArrayAdapter<String>(getApplicationContext(),R.layout.lista,lista_usuarios4);
                listusuarios.setAdapter(adaptador_user);
                listusuarios.setVisibility(View.VISIBLE);
                quadrante =4;
                botao_quadrante1.setBackgroundColor(Color.LTGRAY);
                botao_quadrante2.setBackgroundColor(Color.LTGRAY);
                botao_quadrante3.setBackgroundColor(Color.LTGRAY);
                botao_quadrante4.setBackgroundColor(Color.YELLOW);
                botao_quadrante5.setBackgroundColor(Color.LTGRAY);
                botao_quadrante6.setBackgroundColor(Color.LTGRAY);
                botao_horario1.setBackgroundColor(Color.LTGRAY);
                botao_horario2.setBackgroundColor(Color.LTGRAY);
                botao_horario3.setBackgroundColor(Color.LTGRAY);
                botao_horario4.setBackgroundColor(Color.LTGRAY);
            }
        });

        botao_quadrante5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adaptador_user = new ArrayAdapter<String>(getApplicationContext(),R.layout.lista,lista_usuarios5);
                listusuarios.setAdapter(adaptador_user);
                listusuarios.setVisibility(View.VISIBLE);
                quadrante =5;
                botao_quadrante1.setBackgroundColor(Color.LTGRAY);
                botao_quadrante2.setBackgroundColor(Color.LTGRAY);
                botao_quadrante3.setBackgroundColor(Color.LTGRAY);
                botao_quadrante4.setBackgroundColor(Color.LTGRAY);
                botao_quadrante5.setBackgroundColor(Color.YELLOW);
                botao_quadrante6.setBackgroundColor(Color.LTGRAY);
                botao_horario1.setBackgroundColor(Color.LTGRAY);
                botao_horario2.setBackgroundColor(Color.LTGRAY);
                botao_horario3.setBackgroundColor(Color.LTGRAY);
                botao_horario4.setBackgroundColor(Color.LTGRAY);
            }
        });

        botao_quadrante6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adaptador_user = new ArrayAdapter<String>(getApplicationContext(),R.layout.lista,lista_usuarios6);
                listusuarios.setAdapter(adaptador_user);
                listusuarios.setVisibility(View.VISIBLE);
                quadrante =6;
                botao_quadrante1.setBackgroundColor(Color.LTGRAY);
                botao_quadrante2.setBackgroundColor(Color.LTGRAY);
                botao_quadrante3.setBackgroundColor(Color.LTGRAY);
                botao_quadrante4.setBackgroundColor(Color.LTGRAY);
                botao_quadrante5.setBackgroundColor(Color.LTGRAY);
                botao_quadrante6.setBackgroundColor(Color.YELLOW);
                botao_horario1.setBackgroundColor(Color.LTGRAY);
                botao_horario2.setBackgroundColor(Color.LTGRAY);
                botao_horario3.setBackgroundColor(Color.LTGRAY);
                botao_horario4.setBackgroundColor(Color.LTGRAY);
            }
        });

        botao_horario1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                botao_horario1.setBackgroundColor(Color.YELLOW);
                botao_horario2.setBackgroundColor(Color.LTGRAY);
                botao_horario3.setBackgroundColor(Color.LTGRAY);
                botao_horario4.setBackgroundColor(Color.LTGRAY);
                botao_inicia_entrega.setEnabled(true);
                int j=0,k=1,l=0;
                String loc_endereco_usuario="",loc_endereco_usuario_historico="";
                boolean bit_ocupado = false;
                lista_usuarios_horarios.clear();
                switch (quadrante){
                    case 1:
                        while (l < k) {
                            k=0;
                            for (int i = 0; i <a; i++) {
                                int index = usuario_lista_quadrante1[i].endereco.indexOf(",");
                                if (usuario_lista_quadrante1[i].horario_entrega.equals("6:00 às 6:10")) {
                                    if (!loc_endereco_usuario.isEmpty()) {
                                        if (!loc_endereco_usuario_historico.contains(usuario_lista_quadrante1[i].endereco.substring(0, index) + "@")) {
                                            if (loc_endereco_usuario.contains(usuario_lista_quadrante1[i].endereco.substring(0, index))) {
                                                lista_usuarios_horarios.add(usuario_lista_quadrante1[i].name + ", endereço:" + usuario_lista_quadrante1[i].endereco + ", referencia" + usuario_lista_quadrante1[i].referencia + ", horário entrega:" + usuario_lista_quadrante1[i].horario_entrega
                                                        + ", quantidade:\nPães: " + usuario_lista_quadrante1[i].quantpaes+"\nQueijo: "+usuario_lista_quadrante1[i].quantqueijo+"\nPresunto: "+usuario_lista_quadrante1[i].quantpresunto
                                                        +"\nManteiga: "+usuario_lista_quadrante1[i].quantmanteiga);
                                                ind_usuarios_horarios[j] = i;
                                                j++;
                                                l++;
                                            } else if (!bit_ocupado) {
                                                bit_ocupado = true;
                                                loc_endereco_usuario = usuario_lista_quadrante1[i].endereco.substring(0, index);
                                                lista_usuarios_horarios.add(usuario_lista_quadrante1[i].name + ", endereço:" + usuario_lista_quadrante1[i].endereco + ", referencia" + usuario_lista_quadrante1[i].referencia + ", horário entrega:" + usuario_lista_quadrante1[i].horario_entrega
                                                        + ", quantidade:\nPães: " + usuario_lista_quadrante1[i].quantpaes+"\nQueijo: "+usuario_lista_quadrante1[i].quantqueijo+"\nPresunto: "+usuario_lista_quadrante1[i].quantpresunto
                                                        +"\nManteiga: "+usuario_lista_quadrante1[i].quantmanteiga);
                                                ind_usuarios_horarios[j] = i;
                                                j++;
                                                l++;
                                            }
                                        } else {

                                        }
                                    } else if (!loc_endereco_usuario_historico.contains(usuario_lista_quadrante1[i].endereco.substring(0, index) + "@")){
                                        bit_ocupado = true;
                                        loc_endereco_usuario = usuario_lista_quadrante1[i].endereco.substring(0, index);
                                        lista_usuarios_horarios.add(usuario_lista_quadrante1[i].name + ", endereço:" + usuario_lista_quadrante1[i].endereco + ", referencia" + usuario_lista_quadrante1[i].referencia + ", horário entrega:" + usuario_lista_quadrante1[i].horario_entrega
                                                + ", quantidade:\nPães: " + usuario_lista_quadrante1[i].quantpaes+"\nQueijo: "+usuario_lista_quadrante1[i].quantqueijo+"\nPresunto: "+usuario_lista_quadrante1[i].quantpresunto
                                                +"\nManteiga: "+usuario_lista_quadrante1[i].quantmanteiga);
                                        ind_usuarios_horarios[j] = i;
                                        j++;
                                        l++;
                                    }
                                    k++;
                                }
                                if (i == a - 1) {
                                    bit_ocupado = false;
                                    loc_endereco_usuario_historico = loc_endereco_usuario_historico+","+loc_endereco_usuario+"@";
                                    loc_endereco_usuario = "";
                                    Log.e("Endereco"," -- "+loc_endereco_usuario_historico);
                                }
                            }
                        }
                        break;
                    case 2:
                        while (l < k) {
                            k=0;
                            for (int i = 0; i < b; i++) {
                                int index = usuario_lista_quadrante2[i].endereco.indexOf(",");
                                if (usuario_lista_quadrante2[i].horario_entrega.equals("6:10 às 6:20")) {
                                    if (!loc_endereco_usuario.isEmpty()) {
                                        if (!loc_endereco_usuario_historico.contains(usuario_lista_quadrante2[i].endereco.substring(0, index) + "@")) {
                                            if (loc_endereco_usuario.contains(usuario_lista_quadrante2[i].endereco.substring(0, index))) {
                                                lista_usuarios_horarios.add(usuario_lista_quadrante2[i].name + ", endereço:" + usuario_lista_quadrante2[i].endereco + ", referencia" + usuario_lista_quadrante2[i].referencia + ", horário entrega:" + usuario_lista_quadrante2[i].horario_entrega
                                                        + ", quantidade:\nPães: " + usuario_lista_quadrante2[i].quantpaes+"\nQueijo: "+usuario_lista_quadrante2[i].quantqueijo+"\nPresunto: "+usuario_lista_quadrante2[i].quantpresunto
                                                        +"\nManteiga: "+usuario_lista_quadrante2[i].quantmanteiga);
                                                ind_usuarios_horarios[j] = i;
                                                j++;
                                                l++;
                                            } else if (!bit_ocupado) {
                                                bit_ocupado = true;
                                                loc_endereco_usuario = usuario_lista_quadrante2[i].endereco.substring(0, index);
                                                lista_usuarios_horarios.add(usuario_lista_quadrante2[i].name + ", endereço:" + usuario_lista_quadrante2[i].endereco + ", referencia" + usuario_lista_quadrante2[i].referencia + ", horário entrega:" + usuario_lista_quadrante2[i].horario_entrega
                                                        + ", quantidade:\nPães: " + usuario_lista_quadrante2[i].quantpaes+"\nQueijo: "+usuario_lista_quadrante2[i].quantqueijo+"\nPresunto: "+usuario_lista_quadrante2[i].quantpresunto
                                                        +"\nManteiga: "+usuario_lista_quadrante2[i].quantmanteiga);
                                                ind_usuarios_horarios[j] = i;
                                                j++;
                                                l++;
                                            }
                                        } else {

                                        }
                                    } else if (!loc_endereco_usuario_historico.contains(usuario_lista_quadrante2[i].endereco.substring(0, index) + "@")){
                                        bit_ocupado = true;
                                        loc_endereco_usuario = usuario_lista_quadrante2[i].endereco.substring(0, index);
                                        lista_usuarios_horarios.add(usuario_lista_quadrante2[i].name + ", endereço:" + usuario_lista_quadrante2[i].endereco + ", referencia" + usuario_lista_quadrante2[i].referencia + ", horário entrega:" + usuario_lista_quadrante2[i].horario_entrega
                                                + ", quantidade:\nPães: " + usuario_lista_quadrante2[i].quantpaes+"\nQueijo: "+usuario_lista_quadrante2[i].quantqueijo+"\nPresunto: "+usuario_lista_quadrante2[i].quantpresunto
                                                +"\nManteiga: "+usuario_lista_quadrante2[i].quantmanteiga);
                                        ind_usuarios_horarios[j] = i;
                                        j++;
                                        l++;
                                    }
                                    k++;
                                }
                                if (i == b - 1) {
                                    bit_ocupado = false;
                                    loc_endereco_usuario_historico = loc_endereco_usuario_historico+","+loc_endereco_usuario+"@";
                                    loc_endereco_usuario = "";
                                    Log.e("Endereco"," -- "+loc_endereco_usuario_historico);
                                }
                            }
                        }
                        break;
                    case 3:
                        while (l < k) {
                            k=0;
                            for (int i = 0; i < c; i++) {
                                int index = usuario_lista_quadrante3[i].endereco.indexOf(",");
                                if (usuario_lista_quadrante3[i].horario_entrega.equals("6:20 às 6:30")) {
                                    if (!loc_endereco_usuario.isEmpty()) {
                                        if (!loc_endereco_usuario_historico.contains(usuario_lista_quadrante3[i].endereco.substring(0, index) + "@")) {
                                            if (loc_endereco_usuario.contains(usuario_lista_quadrante3[i].endereco.substring(0, index))) {
                                                lista_usuarios_horarios.add(usuario_lista_quadrante3[i].name + ", endereço:" + usuario_lista_quadrante3[i].endereco + ", referencia" + usuario_lista_quadrante3[i].referencia + ", horário entrega:" + usuario_lista_quadrante3[i].horario_entrega
                                                        + ", quantidade:\nPães: " + usuario_lista_quadrante3[i].quantpaes+"\nQueijo: "+usuario_lista_quadrante3[i].quantqueijo+"\nPresunto: "+usuario_lista_quadrante3[i].quantpresunto
                                                        +"\nManteiga: "+usuario_lista_quadrante3[i].quantmanteiga);
                                                ind_usuarios_horarios[j] = i;
                                                j++;
                                                l++;
                                            } else if (!bit_ocupado) {
                                                bit_ocupado = true;
                                                loc_endereco_usuario = usuario_lista_quadrante3[i].endereco.substring(0, index);
                                                lista_usuarios_horarios.add(usuario_lista_quadrante3[i].name + ", endereço:" + usuario_lista_quadrante3[i].endereco + ", referencia" + usuario_lista_quadrante3[i].referencia + ", horário entrega:" + usuario_lista_quadrante3[i].horario_entrega
                                                        + ", quantidade:\nPães: " + usuario_lista_quadrante3[i].quantpaes+"\nQueijo: "+usuario_lista_quadrante3[i].quantqueijo+"\nPresunto: "+usuario_lista_quadrante3[i].quantpresunto
                                                        +"\nManteiga: "+usuario_lista_quadrante3[i].quantmanteiga);
                                                ind_usuarios_horarios[j] = i;
                                                j++;
                                                l++;
                                            }
                                        } else {

                                        }
                                    } else if (!loc_endereco_usuario_historico.contains(usuario_lista_quadrante3[i].endereco.substring(0, index) + "@")){
                                        bit_ocupado = true;
                                        loc_endereco_usuario = usuario_lista_quadrante3[i].endereco.substring(0, index);
                                        lista_usuarios_horarios.add(usuario_lista_quadrante3[i].name + ", endereço:" + usuario_lista_quadrante3[i].endereco + ", referencia" + usuario_lista_quadrante3[i].referencia + ", horário entrega:" + usuario_lista_quadrante3[i].horario_entrega
                                                + ", quantidade:\nPães: " + usuario_lista_quadrante3[i].quantpaes+"\nQueijo: "+usuario_lista_quadrante3[i].quantqueijo+"\nPresunto: "+usuario_lista_quadrante3[i].quantpresunto
                                                +"\nManteiga: "+usuario_lista_quadrante3[i].quantmanteiga);
                                        ind_usuarios_horarios[j] = i;
                                        j++;
                                        l++;
                                    }
                                    k++;
                                }
                                if (i == c - 1) {
                                    bit_ocupado = false;
                                    loc_endereco_usuario_historico = loc_endereco_usuario_historico+","+loc_endereco_usuario+"@";
                                    loc_endereco_usuario = "";
                                    Log.e("Endereco"," -- "+loc_endereco_usuario_historico);
                                }
                            }
                        }
                        break;
                    case 4:
                        while (l < k) {
                            k=0;
                            for (int i = 0; i < d; i++) {
                                int index = usuario_lista_quadrante4[i].endereco.indexOf(",");
                                if (usuario_lista_quadrante4[i].horario_entrega.equals("6:30 às 6:40")) {
                                    if (!loc_endereco_usuario.isEmpty()) {
                                        if (!loc_endereco_usuario_historico.contains(usuario_lista_quadrante4[i].endereco.substring(0, index) + "@")) {
                                            if (loc_endereco_usuario.contains(usuario_lista_quadrante4[i].endereco.substring(0, index))) {
                                                lista_usuarios_horarios.add(usuario_lista_quadrante4[i].name + ", endereço:" + usuario_lista_quadrante4[i].endereco + ", referencia" + usuario_lista_quadrante4[i].referencia + ", horário entrega:" + usuario_lista_quadrante4[i].horario_entrega
                                                        + ", quantidade:\nPães: " + usuario_lista_quadrante4[i].quantpaes+"\nQueijo: "+usuario_lista_quadrante4[i].quantqueijo+"\nPresunto: "+usuario_lista_quadrante4[i].quantpresunto
                                                        +"\nManteiga: "+usuario_lista_quadrante4[i].quantmanteiga);
                                                ind_usuarios_horarios[j] = i;
                                                j++;
                                                l++;
                                            } else if (!bit_ocupado) {
                                                bit_ocupado = true;
                                                loc_endereco_usuario = usuario_lista_quadrante4[i].endereco.substring(0, index);
                                                lista_usuarios_horarios.add(usuario_lista_quadrante4[i].name + ", endereço:" + usuario_lista_quadrante4[i].endereco + ", referencia" + usuario_lista_quadrante4[i].referencia + ", horário entrega:" + usuario_lista_quadrante4[i].horario_entrega
                                                        + ", quantidade:\nPães: " + usuario_lista_quadrante4[i].quantpaes+"\nQueijo: "+usuario_lista_quadrante4[i].quantqueijo+"\nPresunto: "+usuario_lista_quadrante4[i].quantpresunto
                                                        +"\nManteiga: "+usuario_lista_quadrante4[i].quantmanteiga);
                                                ind_usuarios_horarios[j] = i;
                                                j++;
                                                l++;
                                            }
                                        } else {

                                        }
                                    } else if (!loc_endereco_usuario_historico.contains(usuario_lista_quadrante4[i].endereco.substring(0, index) + "@")){
                                        bit_ocupado = true;
                                        loc_endereco_usuario = usuario_lista_quadrante4[i].endereco.substring(0, index);
                                        lista_usuarios_horarios.add(usuario_lista_quadrante4[i].name + ", endereço:" + usuario_lista_quadrante4[i].endereco + ", referencia" + usuario_lista_quadrante4[i].referencia + ", horário entrega:" + usuario_lista_quadrante4[i].horario_entrega
                                                + ", quantidade:\nPães: " + usuario_lista_quadrante4[i].quantpaes+"\nQueijo: "+usuario_lista_quadrante4[i].quantqueijo+"\nPresunto: "+usuario_lista_quadrante4[i].quantpresunto
                                                +"\nManteiga: "+usuario_lista_quadrante4[i].quantmanteiga);
                                        ind_usuarios_horarios[j] = i;
                                        j++;
                                        l++;
                                    }
                                    k++;
                                }
                                if (i == d - 1) {
                                    bit_ocupado = false;
                                    loc_endereco_usuario_historico = loc_endereco_usuario_historico+","+loc_endereco_usuario+"@";
                                    loc_endereco_usuario = "";
                                    Log.e("Endereco"," -- "+loc_endereco_usuario_historico);
                                }
                            }
                        }
                        break;
                    case 5:
                        while (l < k) {
                            k=0;
                            for (int i = 0; i < e; i++) {
                                int index = usuario_lista_quadrante5[i].endereco.indexOf(",");
                                if (usuario_lista_quadrante5[i].horario_entrega.equals("6:40 às 6:50")) {
                                    if (!loc_endereco_usuario.isEmpty()) {
                                        if (!loc_endereco_usuario_historico.contains(usuario_lista_quadrante5[i].endereco.substring(0, index) + "@")) {
                                            if (loc_endereco_usuario.contains(usuario_lista_quadrante5[i].endereco.substring(0, index))) {
                                                lista_usuarios_horarios.add(usuario_lista_quadrante5[i].name + ", endereço:" + usuario_lista_quadrante5[i].endereco + ", referencia" + usuario_lista_quadrante5[i].referencia + ", horário entrega:" + usuario_lista_quadrante5[i].horario_entrega
                                                        + ", quantidade:\nPães: " + usuario_lista_quadrante5[i].quantpaes+"\nQueijo: "+usuario_lista_quadrante5[i].quantqueijo+"\nPresunto: "+usuario_lista_quadrante5[i].quantpresunto
                                                        +"\nManteiga: "+usuario_lista_quadrante5[i].quantmanteiga);
                                                ind_usuarios_horarios[j] = i;
                                                j++;
                                                l++;
                                            } else if (!bit_ocupado) {
                                                bit_ocupado = true;
                                                loc_endereco_usuario = usuario_lista_quadrante5[i].endereco.substring(0, index);
                                                lista_usuarios_horarios.add(usuario_lista_quadrante5[i].name + ", endereço:" + usuario_lista_quadrante5[i].endereco + ", referencia" + usuario_lista_quadrante5[i].referencia + ", horário entrega:" + usuario_lista_quadrante5[i].horario_entrega
                                                        + ", quantidade:\nPães: " + usuario_lista_quadrante5[i].quantpaes+"\nQueijo: "+usuario_lista_quadrante5[i].quantqueijo+"\nPresunto: "+usuario_lista_quadrante5[i].quantpresunto
                                                        +"\nManteiga: "+usuario_lista_quadrante5[i].quantmanteiga);
                                                ind_usuarios_horarios[j] = i;
                                                j++;
                                                l++;
                                            }
                                        } else {

                                        }
                                    } else if (!loc_endereco_usuario_historico.contains(usuario_lista_quadrante5[i].endereco.substring(0, index) + "@")){
                                        bit_ocupado = true;
                                        loc_endereco_usuario = usuario_lista_quadrante5[i].endereco.substring(0, index);
                                        lista_usuarios_horarios.add(usuario_lista_quadrante5[i].name + ", endereço:" + usuario_lista_quadrante5[i].endereco + ", referencia" + usuario_lista_quadrante5[i].referencia + ", horário entrega:" + usuario_lista_quadrante5[i].horario_entrega
                                                + ", quantidade:\nPães: " + usuario_lista_quadrante5[i].quantpaes+"\nQueijo: "+usuario_lista_quadrante5[i].quantqueijo+"\nPresunto: "+usuario_lista_quadrante5[i].quantpresunto
                                                +"\nManteiga: "+usuario_lista_quadrante5[i].quantmanteiga);
                                        ind_usuarios_horarios[j] = i;
                                        j++;
                                        l++;
                                    }
                                    k++;
                                }
                                if (i == e - 1) {
                                    bit_ocupado = false;
                                    loc_endereco_usuario_historico = loc_endereco_usuario_historico+","+loc_endereco_usuario+"@";
                                    loc_endereco_usuario = "";
                                    Log.e("Endereco"," -- "+loc_endereco_usuario_historico);
                                }
                            }
                        }
                        break;
                    case 6:
                        while (l < k) {
                            k=0;
                            for (int i = 0; i < f; i++) {
                                int index = usuario_lista_quadrante6[i].endereco.indexOf(",");
                                if (usuario_lista_quadrante6[i].horario_entrega.equals("6:50 às 7:00")) {
                                    if (!loc_endereco_usuario.isEmpty()) {
                                        if (!loc_endereco_usuario_historico.contains(usuario_lista_quadrante6[i].endereco.substring(0, index) + "@")) {
                                            if (loc_endereco_usuario.contains(usuario_lista_quadrante6[i].endereco.substring(0, index))) {
                                                lista_usuarios_horarios.add(usuario_lista_quadrante6[i].name + ", endereço:" + usuario_lista_quadrante6[i].endereco + ", referencia" + usuario_lista_quadrante6[i].referencia + ", horário entrega:" + usuario_lista_quadrante6[i].horario_entrega
                                                        + ", quantidade:\nPães: " + usuario_lista_quadrante6[i].quantpaes+"\nQueijo: "+usuario_lista_quadrante6[i].quantqueijo+"\nPresunto: "+usuario_lista_quadrante6[i].quantpresunto
                                                        +"\nManteiga: "+usuario_lista_quadrante6[i].quantmanteiga);
                                                ind_usuarios_horarios[j] = i;
                                                j++;
                                                l++;
                                            } else if (!bit_ocupado) {
                                                bit_ocupado = true;
                                                loc_endereco_usuario = usuario_lista_quadrante6[i].endereco.substring(0, index);
                                                lista_usuarios_horarios.add(usuario_lista_quadrante6[i].name + ", endereço:" + usuario_lista_quadrante6[i].endereco + ", referencia" + usuario_lista_quadrante6[i].referencia + ", horário entrega:" + usuario_lista_quadrante6[i].horario_entrega
                                                        + ", quantidade:\nPães: " + usuario_lista_quadrante6[i].quantpaes+"\nQueijo: "+usuario_lista_quadrante6[i].quantqueijo+"\nPresunto: "+usuario_lista_quadrante6[i].quantpresunto
                                                        +"\nManteiga: "+usuario_lista_quadrante6[i].quantmanteiga);
                                                ind_usuarios_horarios[j] = i;
                                                j++;
                                                l++;
                                            }
                                        } else {

                                        }
                                    } else if (!loc_endereco_usuario_historico.contains(usuario_lista_quadrante6[i].endereco.substring(0, index) + "@")){
                                        bit_ocupado = true;
                                        loc_endereco_usuario = usuario_lista_quadrante6[i].endereco.substring(0, index);
                                        lista_usuarios_horarios.add(usuario_lista_quadrante6[i].name + ", endereço:" + usuario_lista_quadrante6[i].endereco + ", referencia" + usuario_lista_quadrante6[i].referencia + ", horário entrega:" + usuario_lista_quadrante6[i].horario_entrega
                                                + ", quantidade:\nPães: " + usuario_lista_quadrante6[i].quantpaes+"\nQueijo: "+usuario_lista_quadrante6[i].quantqueijo+"\nPresunto: "+usuario_lista_quadrante6[i].quantpresunto
                                                +"\nManteiga: "+usuario_lista_quadrante6[i].quantmanteiga);
                                        ind_usuarios_horarios[j] = i;
                                        j++;
                                        l++;
                                    }
                                    k++;
                                }
                                if (i == f - 1) {
                                    bit_ocupado = false;
                                    loc_endereco_usuario_historico = loc_endereco_usuario_historico+","+loc_endereco_usuario+"@";
                                    loc_endereco_usuario = "";
                                    Log.e("Endereco"," -- "+loc_endereco_usuario_historico);
                                }
                            }
                        }
                        break;
                }
                adaptador_user_horario = new ArrayAdapter<String>(getApplicationContext(),R.layout.lista,lista_usuarios_horarios);
                listusuarios.setAdapter(adaptador_user_horario);
                listusuarios.setVisibility(View.VISIBLE);
            }
        });

        botao_horario2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                botao_horario1.setBackgroundColor(Color.LTGRAY);
                botao_horario2.setBackgroundColor(Color.YELLOW);
                botao_horario3.setBackgroundColor(Color.LTGRAY);
                botao_horario4.setBackgroundColor(Color.LTGRAY);
                botao_inicia_entrega.setEnabled(true);
                int j=0,k=1,l=0;
                String loc_endereco_usuario="",loc_endereco_usuario_historico="";
                boolean bit_ocupado = false;
                lista_usuarios_horarios.clear();
                switch (quadrante){
                    case 1:
                        while (l < k) {
                            k=0;
                            for (int i = 0; i <a; i++) {
                                int index = usuario_lista_quadrante1[i].endereco.indexOf(",");
                                if (usuario_lista_quadrante1[i].horario_entrega.equals("7:00 às 7:10")) {
                                    if (!loc_endereco_usuario.isEmpty()) {
                                        if (!loc_endereco_usuario_historico.contains(usuario_lista_quadrante1[i].endereco.substring(0, index) + "@")) {
                                            if (loc_endereco_usuario.contains(usuario_lista_quadrante1[i].endereco.substring(0, index))) {
                                                lista_usuarios_horarios.add(usuario_lista_quadrante1[i].name + ", endereço:" + usuario_lista_quadrante1[i].endereco + ", referencia" + usuario_lista_quadrante1[i].referencia + ", horário entrega:" + usuario_lista_quadrante1[i].horario_entrega
                                                        + ", quantidade:\nPães: " + usuario_lista_quadrante1[i].quantpaes+"\nQueijo: "+usuario_lista_quadrante1[i].quantqueijo+"\nPresunto: "+usuario_lista_quadrante1[i].quantpresunto
                                                        +"\nManteiga: "+usuario_lista_quadrante1[i].quantmanteiga);
                                                ind_usuarios_horarios[j] = i;
                                                j++;
                                                l++;
                                            } else if (!bit_ocupado) {
                                                bit_ocupado = true;
                                                loc_endereco_usuario = usuario_lista_quadrante1[i].endereco.substring(0, index);
                                                lista_usuarios_horarios.add(usuario_lista_quadrante1[i].name + ", endereço:" + usuario_lista_quadrante1[i].endereco + ", referencia" + usuario_lista_quadrante1[i].referencia + ", horário entrega:" + usuario_lista_quadrante1[i].horario_entrega
                                                        + ", quantidade:\nPães: " + usuario_lista_quadrante1[i].quantpaes+"\nQueijo: "+usuario_lista_quadrante1[i].quantqueijo+"\nPresunto: "+usuario_lista_quadrante1[i].quantpresunto
                                                        +"\nManteiga: "+usuario_lista_quadrante1[i].quantmanteiga);
                                                ind_usuarios_horarios[j] = i;
                                                j++;
                                                l++;
                                            }
                                        } else {

                                        }
                                    } else if (!loc_endereco_usuario_historico.contains(usuario_lista_quadrante1[i].endereco.substring(0, index) + "@")){
                                        bit_ocupado = true;
                                        loc_endereco_usuario = usuario_lista_quadrante1[i].endereco.substring(0, index);
                                        lista_usuarios_horarios.add(usuario_lista_quadrante1[i].name + ", endereço:" + usuario_lista_quadrante1[i].endereco + ", referencia" + usuario_lista_quadrante1[i].referencia + ", horário entrega:" + usuario_lista_quadrante1[i].horario_entrega
                                                + ", quantidade:\nPães: " + usuario_lista_quadrante1[i].quantpaes+"\nQueijo: "+usuario_lista_quadrante1[i].quantqueijo+"\nPresunto: "+usuario_lista_quadrante1[i].quantpresunto
                                                +"\nManteiga: "+usuario_lista_quadrante1[i].quantmanteiga);
                                        ind_usuarios_horarios[j] = i;
                                        j++;
                                        l++;
                                    }
                                    k++;
                                }
                                if (i == a - 1) {
                                    bit_ocupado = false;
                                    loc_endereco_usuario_historico = loc_endereco_usuario_historico+","+loc_endereco_usuario+"@";
                                    loc_endereco_usuario = "";
                                    Log.e("Endereco"," -- "+loc_endereco_usuario_historico);
                                }
                            }
                        }
                        break;
                    case 2:
                        while (l < k) {
                            k=0;
                            for (int i = 0; i < b; i++) {
                                int index = usuario_lista_quadrante2[i].endereco.indexOf(",");
                                if (usuario_lista_quadrante2[i].horario_entrega.equals("7:10 às 7:20")) {
                                    if (!loc_endereco_usuario.isEmpty()) {
                                        if (!loc_endereco_usuario_historico.contains(usuario_lista_quadrante2[i].endereco.substring(0, index) + "@")) {
                                            if (loc_endereco_usuario.contains(usuario_lista_quadrante2[i].endereco.substring(0, index))) {
                                                lista_usuarios_horarios.add(usuario_lista_quadrante2[i].name + ", endereço:" + usuario_lista_quadrante2[i].endereco + ", referencia" + usuario_lista_quadrante2[i].referencia + ", horário entrega:" + usuario_lista_quadrante2[i].horario_entrega
                                                        + ", quantidade:\nPães: " + usuario_lista_quadrante2[i].quantpaes+"\nQueijo: "+usuario_lista_quadrante2[i].quantqueijo+"\nPresunto: "+usuario_lista_quadrante2[i].quantpresunto
                                                        +"\nManteiga: "+usuario_lista_quadrante2[i].quantmanteiga);
                                                ind_usuarios_horarios[j] = i;
                                                j++;
                                                l++;
                                            } else if (!bit_ocupado) {
                                                bit_ocupado = true;
                                                loc_endereco_usuario = usuario_lista_quadrante2[i].endereco.substring(0, index);
                                                lista_usuarios_horarios.add(usuario_lista_quadrante2[i].name + ", endereço:" + usuario_lista_quadrante2[i].endereco + ", referencia" + usuario_lista_quadrante2[i].referencia + ", horário entrega:" + usuario_lista_quadrante2[i].horario_entrega
                                                        + ", quantidade:\nPães: " + usuario_lista_quadrante2[i].quantpaes+"\nQueijo: "+usuario_lista_quadrante2[i].quantqueijo+"\nPresunto: "+usuario_lista_quadrante2[i].quantpresunto
                                                        +"\nManteiga: "+usuario_lista_quadrante2[i].quantmanteiga);
                                                ind_usuarios_horarios[j] = i;
                                                j++;
                                                l++;
                                            }
                                        } else {

                                        }
                                    } else if (!loc_endereco_usuario_historico.contains(usuario_lista_quadrante2[i].endereco.substring(0, index) + "@")){
                                        bit_ocupado = true;
                                        loc_endereco_usuario = usuario_lista_quadrante2[i].endereco.substring(0, index);
                                        lista_usuarios_horarios.add(usuario_lista_quadrante2[i].name + ", endereço:" + usuario_lista_quadrante2[i].endereco + ", referencia" + usuario_lista_quadrante2[i].referencia + ", horário entrega:" + usuario_lista_quadrante2[i].horario_entrega
                                                + ", quantidade:\nPães: " + usuario_lista_quadrante2[i].quantpaes+"\nQueijo: "+usuario_lista_quadrante2[i].quantqueijo+"\nPresunto: "+usuario_lista_quadrante2[i].quantpresunto
                                                +"\nManteiga: "+usuario_lista_quadrante2[i].quantmanteiga);
                                        ind_usuarios_horarios[j] = i;
                                        j++;
                                        l++;
                                    }
                                    k++;
                                }
                                if (i == b - 1) {
                                    bit_ocupado = false;
                                    loc_endereco_usuario_historico = loc_endereco_usuario_historico+","+loc_endereco_usuario+"@";
                                    loc_endereco_usuario = "";
                                    Log.e("Endereco"," -- "+loc_endereco_usuario_historico);
                                }
                            }
                        }
                        break;
                    case 3:
                        while (l < k) {
                            k=0;
                            for (int i = 0; i < c; i++) {
                                int index = usuario_lista_quadrante3[i].endereco.indexOf(",");
                                if (usuario_lista_quadrante3[i].horario_entrega.equals("7:20 às 7:30")) {
                                    if (!loc_endereco_usuario.isEmpty()) {
                                        if (!loc_endereco_usuario_historico.contains(usuario_lista_quadrante3[i].endereco.substring(0, index) + "@")) {
                                            if (loc_endereco_usuario.contains(usuario_lista_quadrante3[i].endereco.substring(0, index))) {
                                                lista_usuarios_horarios.add(usuario_lista_quadrante3[i].name + ", endereço:" + usuario_lista_quadrante3[i].endereco + ", referencia" + usuario_lista_quadrante3[i].referencia + ", horário entrega:" + usuario_lista_quadrante3[i].horario_entrega
                                                        + ", quantidade:\nPães: " + usuario_lista_quadrante3[i].quantpaes+"\nQueijo: "+usuario_lista_quadrante3[i].quantqueijo+"\nPresunto: "+usuario_lista_quadrante3[i].quantpresunto
                                                        +"\nManteiga: "+usuario_lista_quadrante3[i].quantmanteiga);
                                                ind_usuarios_horarios[j] = i;
                                                j++;
                                                l++;
                                            } else if (!bit_ocupado) {
                                                bit_ocupado = true;
                                                loc_endereco_usuario = usuario_lista_quadrante3[i].endereco.substring(0, index);
                                                lista_usuarios_horarios.add(usuario_lista_quadrante3[i].name + ", endereço:" + usuario_lista_quadrante3[i].endereco + ", referencia" + usuario_lista_quadrante3[i].referencia + ", horário entrega:" + usuario_lista_quadrante3[i].horario_entrega
                                                        + ", quantidade:\nPães: " + usuario_lista_quadrante3[i].quantpaes+"\nQueijo: "+usuario_lista_quadrante3[i].quantqueijo+"\nPresunto: "+usuario_lista_quadrante3[i].quantpresunto
                                                        +"\nManteiga: "+usuario_lista_quadrante3[i].quantmanteiga);
                                                ind_usuarios_horarios[j] = i;
                                                j++;
                                                l++;
                                            }
                                        } else {

                                        }
                                    } else if (!loc_endereco_usuario_historico.contains(usuario_lista_quadrante3[i].endereco.substring(0, index) + "@")){
                                        bit_ocupado = true;
                                        loc_endereco_usuario = usuario_lista_quadrante3[i].endereco.substring(0, index);
                                        lista_usuarios_horarios.add(usuario_lista_quadrante3[i].name + ", endereço:" + usuario_lista_quadrante3[i].endereco + ", referencia" + usuario_lista_quadrante3[i].referencia + ", horário entrega:" + usuario_lista_quadrante3[i].horario_entrega
                                                + ", quantidade:\nPães: " + usuario_lista_quadrante3[i].quantpaes+"\nQueijo: "+usuario_lista_quadrante3[i].quantqueijo+"\nPresunto: "+usuario_lista_quadrante3[i].quantpresunto
                                                +"\nManteiga: "+usuario_lista_quadrante3[i].quantmanteiga);
                                        ind_usuarios_horarios[j] = i;
                                        j++;
                                        l++;
                                    }
                                    k++;
                                }
                                if (i == c - 1) {
                                    bit_ocupado = false;
                                    loc_endereco_usuario_historico = loc_endereco_usuario_historico+","+loc_endereco_usuario+"@";
                                    loc_endereco_usuario = "";
                                    Log.e("Endereco"," -- "+loc_endereco_usuario_historico);
                                }
                            }
                        }
                        break;
                    case 4:
                        while (l < k) {
                            k=0;
                            for (int i = 0; i < d; i++) {
                                int index = usuario_lista_quadrante4[i].endereco.indexOf(",");
                                if (usuario_lista_quadrante4[i].horario_entrega.equals("7:30 às 7:40")) {
                                    if (!loc_endereco_usuario.isEmpty()) {
                                        if (!loc_endereco_usuario_historico.contains(usuario_lista_quadrante4[i].endereco.substring(0, index) + "@")) {
                                            if (loc_endereco_usuario.contains(usuario_lista_quadrante4[i].endereco.substring(0, index))) {
                                                lista_usuarios_horarios.add(usuario_lista_quadrante4[i].name + ", endereço:" + usuario_lista_quadrante4[i].endereco + ", referencia" + usuario_lista_quadrante4[i].referencia + ", horário entrega:" + usuario_lista_quadrante4[i].horario_entrega
                                                        + ", quantidade:\nPães: " + usuario_lista_quadrante4[i].quantpaes+"\nQueijo: "+usuario_lista_quadrante4[i].quantqueijo+"\nPresunto: "+usuario_lista_quadrante4[i].quantpresunto
                                                        +"\nManteiga: "+usuario_lista_quadrante4[i].quantmanteiga);
                                                ind_usuarios_horarios[j] = i;
                                                j++;
                                                l++;
                                            } else if (!bit_ocupado) {
                                                bit_ocupado = true;
                                                loc_endereco_usuario = usuario_lista_quadrante4[i].endereco.substring(0, index);
                                                lista_usuarios_horarios.add(usuario_lista_quadrante4[i].name + ", endereço:" + usuario_lista_quadrante4[i].endereco + ", referencia" + usuario_lista_quadrante4[i].referencia + ", horário entrega:" + usuario_lista_quadrante4[i].horario_entrega
                                                        + ", quantidade:\nPães: " + usuario_lista_quadrante4[i].quantpaes+"\nQueijo: "+usuario_lista_quadrante4[i].quantqueijo+"\nPresunto: "+usuario_lista_quadrante4[i].quantpresunto
                                                        +"\nManteiga: "+usuario_lista_quadrante4[i].quantmanteiga);
                                                ind_usuarios_horarios[j] = i;
                                                j++;
                                                l++;
                                            }
                                        } else {

                                        }
                                    } else if (!loc_endereco_usuario_historico.contains(usuario_lista_quadrante4[i].endereco.substring(0, index) + "@")){
                                        bit_ocupado = true;
                                        loc_endereco_usuario = usuario_lista_quadrante4[i].endereco.substring(0, index);
                                        lista_usuarios_horarios.add(usuario_lista_quadrante4[i].name + ", endereço:" + usuario_lista_quadrante4[i].endereco + ", referencia" + usuario_lista_quadrante4[i].referencia + ", horário entrega:" + usuario_lista_quadrante4[i].horario_entrega
                                                + ", quantidade:\nPães: " + usuario_lista_quadrante4[i].quantpaes+"\nQueijo: "+usuario_lista_quadrante4[i].quantqueijo+"\nPresunto: "+usuario_lista_quadrante4[i].quantpresunto
                                                +"\nManteiga: "+usuario_lista_quadrante4[i].quantmanteiga);
                                        ind_usuarios_horarios[j] = i;
                                        j++;
                                        l++;
                                    }
                                    k++;
                                }
                                if (i == d - 1) {
                                    bit_ocupado = false;
                                    loc_endereco_usuario_historico = loc_endereco_usuario_historico+","+loc_endereco_usuario+"@";
                                    loc_endereco_usuario = "";
                                    Log.e("Endereco"," -- "+loc_endereco_usuario_historico);
                                }
                            }
                        }
                        break;
                    case 5:
                        while (l < k) {
                            k=0;
                            for (int i = 0; i < e; i++) {
                                int index = usuario_lista_quadrante5[i].endereco.indexOf(",");
                                if (usuario_lista_quadrante5[i].horario_entrega.equals("7:40 às 7:50")) {
                                    if (!loc_endereco_usuario.isEmpty()) {
                                        if (!loc_endereco_usuario_historico.contains(usuario_lista_quadrante5[i].endereco.substring(0, index) + "@")) {
                                            if (loc_endereco_usuario.contains(usuario_lista_quadrante5[i].endereco.substring(0, index))) {
                                                lista_usuarios_horarios.add(usuario_lista_quadrante5[i].name + ", endereço:" + usuario_lista_quadrante5[i].endereco + ", referencia" + usuario_lista_quadrante5[i].referencia + ", horário entrega:" + usuario_lista_quadrante5[i].horario_entrega
                                                        + ", quantidade:\nPães: " + usuario_lista_quadrante5[i].quantpaes+"\nQueijo: "+usuario_lista_quadrante5[i].quantqueijo+"\nPresunto: "+usuario_lista_quadrante5[i].quantpresunto
                                                        +"\nManteiga: "+usuario_lista_quadrante5[i].quantmanteiga);
                                                ind_usuarios_horarios[j] = i;
                                                j++;
                                                l++;
                                            } else if (!bit_ocupado) {
                                                bit_ocupado = true;
                                                loc_endereco_usuario = usuario_lista_quadrante5[i].endereco.substring(0, index);
                                                lista_usuarios_horarios.add(usuario_lista_quadrante5[i].name + ", endereço:" + usuario_lista_quadrante5[i].endereco + ", referencia" + usuario_lista_quadrante5[i].referencia + ", horário entrega:" + usuario_lista_quadrante5[i].horario_entrega
                                                        + ", quantidade:\nPães: " + usuario_lista_quadrante5[i].quantpaes+"\nQueijo: "+usuario_lista_quadrante5[i].quantqueijo+"\nPresunto: "+usuario_lista_quadrante5[i].quantpresunto
                                                        +"\nManteiga: "+usuario_lista_quadrante5[i].quantmanteiga);
                                                ind_usuarios_horarios[j] = i;
                                                j++;
                                                l++;
                                            }
                                        } else {

                                        }
                                    } else if (!loc_endereco_usuario_historico.contains(usuario_lista_quadrante5[i].endereco.substring(0, index) + "@")){
                                        bit_ocupado = true;
                                        loc_endereco_usuario = usuario_lista_quadrante5[i].endereco.substring(0, index);
                                        lista_usuarios_horarios.add(usuario_lista_quadrante5[i].name + ", endereço:" + usuario_lista_quadrante5[i].endereco + ", referencia" + usuario_lista_quadrante5[i].referencia + ", horário entrega:" + usuario_lista_quadrante5[i].horario_entrega
                                                + ", quantidade:\nPães: " + usuario_lista_quadrante5[i].quantpaes+"\nQueijo: "+usuario_lista_quadrante5[i].quantqueijo+"\nPresunto: "+usuario_lista_quadrante5[i].quantpresunto
                                                +"\nManteiga: "+usuario_lista_quadrante5[i].quantmanteiga);
                                        ind_usuarios_horarios[j] = i;
                                        j++;
                                        l++;
                                    }
                                    k++;
                                }
                                if (i == e - 1) {
                                    bit_ocupado = false;
                                    loc_endereco_usuario_historico = loc_endereco_usuario_historico+","+loc_endereco_usuario+"@";
                                    loc_endereco_usuario = "";
                                    Log.e("Endereco"," -- "+loc_endereco_usuario_historico);
                                }
                            }
                        }
                        break;
                    case 6:
                        while (l < k) {
                            k=0;
                            for (int i = 0; i < f; i++) {
                                int index = usuario_lista_quadrante6[i].endereco.indexOf(",");
                                if (usuario_lista_quadrante6[i].horario_entrega.equals("7:50 às 8:00")) {
                                    if (!loc_endereco_usuario.isEmpty()) {
                                        if (!loc_endereco_usuario_historico.contains(usuario_lista_quadrante6[i].endereco.substring(0, index) + "@")) {
                                            if (loc_endereco_usuario.contains(usuario_lista_quadrante6[i].endereco.substring(0, index))) {
                                                lista_usuarios_horarios.add(usuario_lista_quadrante6[i].name + ", endereço:" + usuario_lista_quadrante6[i].endereco + ", referencia" + usuario_lista_quadrante6[i].referencia + ", horário entrega:" + usuario_lista_quadrante6[i].horario_entrega
                                                        + ", quantidade:\nPães: " + usuario_lista_quadrante6[i].quantpaes+"\nQueijo: "+usuario_lista_quadrante6[i].quantqueijo+"\nPresunto: "+usuario_lista_quadrante6[i].quantpresunto
                                                        +"\nManteiga: "+usuario_lista_quadrante6[i].quantmanteiga);
                                                ind_usuarios_horarios[j] = i;
                                                j++;
                                                l++;
                                            } else if (!bit_ocupado) {
                                                bit_ocupado = true;
                                                loc_endereco_usuario = usuario_lista_quadrante6[i].endereco.substring(0, index);
                                                lista_usuarios_horarios.add(usuario_lista_quadrante6[i].name + ", endereço:" + usuario_lista_quadrante6[i].endereco + ", referencia" + usuario_lista_quadrante6[i].referencia + ", horário entrega:" + usuario_lista_quadrante6[i].horario_entrega
                                                        + ", quantidade:\nPães: " + usuario_lista_quadrante6[i].quantpaes+"\nQueijo: "+usuario_lista_quadrante6[i].quantqueijo+"\nPresunto: "+usuario_lista_quadrante6[i].quantpresunto
                                                        +"\nManteiga: "+usuario_lista_quadrante6[i].quantmanteiga);
                                                ind_usuarios_horarios[j] = i;
                                                j++;
                                                l++;
                                            }
                                        } else {

                                        }
                                    } else if (!loc_endereco_usuario_historico.contains(usuario_lista_quadrante6[i].endereco.substring(0, index) + "@")){
                                        bit_ocupado = true;
                                        loc_endereco_usuario = usuario_lista_quadrante6[i].endereco.substring(0, index);
                                        lista_usuarios_horarios.add(usuario_lista_quadrante6[i].name + ", endereço:" + usuario_lista_quadrante6[i].endereco + ", referencia" + usuario_lista_quadrante6[i].referencia + ", horário entrega:" + usuario_lista_quadrante6[i].horario_entrega
                                                + ", quantidade:\nPães: " + usuario_lista_quadrante6[i].quantpaes+"\nQueijo: "+usuario_lista_quadrante6[i].quantqueijo+"\nPresunto: "+usuario_lista_quadrante6[i].quantpresunto
                                                +"\nManteiga: "+usuario_lista_quadrante6[i].quantmanteiga);
                                        ind_usuarios_horarios[j] = i;
                                        j++;
                                        l++;
                                    }
                                    k++;
                                }
                                if (i == f - 1) {
                                    bit_ocupado = false;
                                    loc_endereco_usuario_historico = loc_endereco_usuario_historico+","+loc_endereco_usuario+"@";
                                    loc_endereco_usuario = "";
                                    Log.e("Endereco"," -- "+loc_endereco_usuario_historico);
                                }
                            }
                        }
                        break;
                }
                adaptador_user_horario = new ArrayAdapter<String>(getApplicationContext(),R.layout.lista,lista_usuarios_horarios);
                listusuarios.setAdapter(adaptador_user_horario);
                listusuarios.setVisibility(View.VISIBLE);
            }
        });

        botao_horario3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                botao_horario1.setBackgroundColor(Color.LTGRAY);
                botao_horario2.setBackgroundColor(Color.LTGRAY);
                botao_horario3.setBackgroundColor(Color.YELLOW);
                botao_horario4.setBackgroundColor(Color.LTGRAY);
                botao_inicia_entrega.setEnabled(true);
                int j=0,k=1,l=0;
                String loc_endereco_usuario="",loc_endereco_usuario_historico="";
                boolean bit_ocupado = false;
                lista_usuarios_horarios.clear();
                switch (quadrante){
                    case 1:
                        while (l < k) {
                            k=0;
                            for (int i = 0; i <a; i++) {
                                int index = usuario_lista_quadrante1[i].endereco.indexOf(",");
                                if (usuario_lista_quadrante1[i].horario_entrega.equals("8:00 às 8:10")) {
                                    if (!loc_endereco_usuario.isEmpty()) {
                                        if (!loc_endereco_usuario_historico.contains(usuario_lista_quadrante1[i].endereco.substring(0, index) + "@")) {
                                            if (loc_endereco_usuario.contains(usuario_lista_quadrante1[i].endereco.substring(0, index))) {
                                                lista_usuarios_horarios.add(usuario_lista_quadrante1[i].name + ", endereço:" + usuario_lista_quadrante1[i].endereco + ", referencia" + usuario_lista_quadrante1[i].referencia + ", horário entrega:" + usuario_lista_quadrante1[i].horario_entrega
                                                        + ", quantidade:\nPães: " + usuario_lista_quadrante1[i].quantpaes+"\nQueijo: "+usuario_lista_quadrante1[i].quantqueijo+"\nPresunto: "+usuario_lista_quadrante1[i].quantpresunto
                                                        +"\nManteiga: "+usuario_lista_quadrante1[i].quantmanteiga);
                                                ind_usuarios_horarios[j] = i;
                                                j++;
                                                l++;
                                            } else if (!bit_ocupado) {
                                                bit_ocupado = true;
                                                loc_endereco_usuario = usuario_lista_quadrante1[i].endereco.substring(0, index);
                                                lista_usuarios_horarios.add(usuario_lista_quadrante1[i].name + ", endereço:" + usuario_lista_quadrante1[i].endereco + ", referencia" + usuario_lista_quadrante1[i].referencia + ", horário entrega:" + usuario_lista_quadrante1[i].horario_entrega
                                                        + ", quantidade:\nPães: " + usuario_lista_quadrante1[i].quantpaes+"\nQueijo: "+usuario_lista_quadrante1[i].quantqueijo+"\nPresunto: "+usuario_lista_quadrante1[i].quantpresunto
                                                        +"\nManteiga: "+usuario_lista_quadrante1[i].quantmanteiga);
                                                ind_usuarios_horarios[j] = i;
                                                j++;
                                                l++;
                                            }
                                        } else {

                                        }
                                    } else if (!loc_endereco_usuario_historico.contains(usuario_lista_quadrante1[i].endereco.substring(0, index) + "@")){
                                        bit_ocupado = true;
                                        loc_endereco_usuario = usuario_lista_quadrante1[i].endereco.substring(0, index);
                                        lista_usuarios_horarios.add(usuario_lista_quadrante1[i].name + ", endereço:" + usuario_lista_quadrante1[i].endereco + ", referencia" + usuario_lista_quadrante1[i].referencia + ", horário entrega:" + usuario_lista_quadrante1[i].horario_entrega
                                                + ", quantidade:\nPães: " + usuario_lista_quadrante1[i].quantpaes+"\nQueijo: "+usuario_lista_quadrante1[i].quantqueijo+"\nPresunto: "+usuario_lista_quadrante1[i].quantpresunto
                                                +"\nManteiga: "+usuario_lista_quadrante1[i].quantmanteiga);
                                        ind_usuarios_horarios[j] = i;
                                        j++;
                                        l++;
                                    }
                                    k++;
                                }
                                if (i == a - 1) {
                                    bit_ocupado = false;
                                    loc_endereco_usuario_historico = loc_endereco_usuario_historico+","+loc_endereco_usuario+"@";
                                    loc_endereco_usuario = "";
                                    Log.e("Endereco"," -- "+loc_endereco_usuario_historico);
                                }
                            }
                        }
                        break;
                    case 2:
                        while (l < k) {
                            k=0;
                            for (int i = 0; i < b; i++) {
                                int index = usuario_lista_quadrante2[i].endereco.indexOf(",");
                                if (usuario_lista_quadrante2[i].horario_entrega.equals("8:10 às 8:20")) {
                                    if (!loc_endereco_usuario.isEmpty()) {
                                        if (!loc_endereco_usuario_historico.contains(usuario_lista_quadrante2[i].endereco.substring(0, index) + "@")) {
                                            if (loc_endereco_usuario.contains(usuario_lista_quadrante2[i].endereco.substring(0, index))) {
                                                lista_usuarios_horarios.add(usuario_lista_quadrante2[i].name + ", endereço:" + usuario_lista_quadrante2[i].endereco + ", referencia" + usuario_lista_quadrante2[i].referencia + ", horário entrega:" + usuario_lista_quadrante2[i].horario_entrega
                                                        + ", quantidade:\nPães: " + usuario_lista_quadrante2[i].quantpaes+"\nQueijo: "+usuario_lista_quadrante2[i].quantqueijo+"\nPresunto: "+usuario_lista_quadrante2[i].quantpresunto
                                                        +"\nManteiga: "+usuario_lista_quadrante2[i].quantmanteiga);
                                                ind_usuarios_horarios[j] = i;
                                                j++;
                                                l++;
                                            } else if (!bit_ocupado) {
                                                bit_ocupado = true;
                                                loc_endereco_usuario = usuario_lista_quadrante2[i].endereco.substring(0, index);
                                                lista_usuarios_horarios.add(usuario_lista_quadrante2[i].name + ", endereço:" + usuario_lista_quadrante2[i].endereco + ", referencia" + usuario_lista_quadrante2[i].referencia + ", horário entrega:" + usuario_lista_quadrante2[i].horario_entrega
                                                        + ", quantidade:\nPães: " + usuario_lista_quadrante2[i].quantpaes+"\nQueijo: "+usuario_lista_quadrante2[i].quantqueijo+"\nPresunto: "+usuario_lista_quadrante2[i].quantpresunto
                                                        +"\nManteiga: "+usuario_lista_quadrante2[i].quantmanteiga);
                                                ind_usuarios_horarios[j] = i;
                                                j++;
                                                l++;
                                            }
                                        } else {

                                        }
                                    } else if (!loc_endereco_usuario_historico.contains(usuario_lista_quadrante2[i].endereco.substring(0, index) + "@")){
                                        bit_ocupado = true;
                                        loc_endereco_usuario = usuario_lista_quadrante2[i].endereco.substring(0, index);
                                        lista_usuarios_horarios.add(usuario_lista_quadrante2[i].name + ", endereço:" + usuario_lista_quadrante2[i].endereco + ", referencia" + usuario_lista_quadrante2[i].referencia + ", horário entrega:" + usuario_lista_quadrante2[i].horario_entrega
                                                + ", quantidade:\nPães: " + usuario_lista_quadrante2[i].quantpaes+"\nQueijo: "+usuario_lista_quadrante2[i].quantqueijo+"\nPresunto: "+usuario_lista_quadrante2[i].quantpresunto
                                                +"\nManteiga: "+usuario_lista_quadrante2[i].quantmanteiga);
                                        ind_usuarios_horarios[j] = i;
                                        j++;
                                        l++;
                                    }
                                    k++;
                                }
                                if (i == b - 1) {
                                    bit_ocupado = false;
                                    loc_endereco_usuario_historico = loc_endereco_usuario_historico+","+loc_endereco_usuario+"@";
                                    loc_endereco_usuario = "";
                                    Log.e("Endereco"," -- "+loc_endereco_usuario_historico);
                                }
                            }
                        }
                        break;
                    case 3:
                        while (l < k) {
                            k=0;
                            for (int i = 0; i < c; i++) {
                                int index = usuario_lista_quadrante3[i].endereco.indexOf(",");
                                if (usuario_lista_quadrante3[i].horario_entrega.equals("8:20 às 8:30")) {
                                    if (!loc_endereco_usuario.isEmpty()) {
                                        if (!loc_endereco_usuario_historico.contains(usuario_lista_quadrante3[i].endereco.substring(0, index) + "@")) {
                                            if (loc_endereco_usuario.contains(usuario_lista_quadrante3[i].endereco.substring(0, index))) {
                                                lista_usuarios_horarios.add(usuario_lista_quadrante3[i].name + ", endereço:" + usuario_lista_quadrante3[i].endereco + ", referencia" + usuario_lista_quadrante3[i].referencia + ", horário entrega:" + usuario_lista_quadrante3[i].horario_entrega
                                                        + ", quantidade:\nPães: " + usuario_lista_quadrante3[i].quantpaes+"\nQueijo: "+usuario_lista_quadrante3[i].quantqueijo+"\nPresunto: "+usuario_lista_quadrante3[i].quantpresunto
                                                        +"\nManteiga: "+usuario_lista_quadrante3[i].quantmanteiga);
                                                ind_usuarios_horarios[j] = i;
                                                j++;
                                                l++;
                                            } else if (!bit_ocupado) {
                                                bit_ocupado = true;
                                                loc_endereco_usuario = usuario_lista_quadrante3[i].endereco.substring(0, index);
                                                lista_usuarios_horarios.add(usuario_lista_quadrante3[i].name + ", endereço:" + usuario_lista_quadrante3[i].endereco + ", referencia" + usuario_lista_quadrante3[i].referencia + ", horário entrega:" + usuario_lista_quadrante3[i].horario_entrega
                                                        + ", quantidade:\nPães: " + usuario_lista_quadrante3[i].quantpaes+"\nQueijo: "+usuario_lista_quadrante3[i].quantqueijo+"\nPresunto: "+usuario_lista_quadrante3[i].quantpresunto
                                                        +"\nManteiga: "+usuario_lista_quadrante3[i].quantmanteiga);
                                                ind_usuarios_horarios[j] = i;
                                                j++;
                                                l++;
                                            }
                                        } else {

                                        }
                                    } else if (!loc_endereco_usuario_historico.contains(usuario_lista_quadrante3[i].endereco.substring(0, index) + "@")){
                                        bit_ocupado = true;
                                        loc_endereco_usuario = usuario_lista_quadrante3[i].endereco.substring(0, index);
                                        lista_usuarios_horarios.add(usuario_lista_quadrante3[i].name + ", endereço:" + usuario_lista_quadrante3[i].endereco + ", referencia" + usuario_lista_quadrante3[i].referencia + ", horário entrega:" + usuario_lista_quadrante3[i].horario_entrega
                                                + ", quantidade:\nPães: " + usuario_lista_quadrante3[i].quantpaes+"\nQueijo: "+usuario_lista_quadrante3[i].quantqueijo+"\nPresunto: "+usuario_lista_quadrante3[i].quantpresunto
                                                +"\nManteiga: "+usuario_lista_quadrante3[i].quantmanteiga);
                                        ind_usuarios_horarios[j] = i;
                                        j++;
                                        l++;
                                    }
                                    k++;
                                }
                                if (i == c - 1) {
                                    bit_ocupado = false;
                                    loc_endereco_usuario_historico = loc_endereco_usuario_historico+","+loc_endereco_usuario+"@";
                                    loc_endereco_usuario = "";
                                    Log.e("Endereco"," -- "+loc_endereco_usuario_historico);
                                }
                            }
                        }
                        break;
                    case 4:
                        while (l < k) {
                            k=0;
                            for (int i = 0; i < d; i++) {
                                int index = usuario_lista_quadrante4[i].endereco.indexOf(",");
                                if (usuario_lista_quadrante4[i].horario_entrega.equals("8:30 às 8:40")) {
                                    if (!loc_endereco_usuario.isEmpty()) {
                                        if (!loc_endereco_usuario_historico.contains(usuario_lista_quadrante4[i].endereco.substring(0, index) + "@")) {
                                            if (loc_endereco_usuario.contains(usuario_lista_quadrante4[i].endereco.substring(0, index))) {
                                                lista_usuarios_horarios.add(usuario_lista_quadrante4[i].name + ", endereço:" + usuario_lista_quadrante4[i].endereco + ", referencia" + usuario_lista_quadrante4[i].referencia + ", horário entrega:" + usuario_lista_quadrante4[i].horario_entrega
                                                        + ", quantidade:\nPães: " + usuario_lista_quadrante4[i].quantpaes+"\nQueijo: "+usuario_lista_quadrante4[i].quantqueijo+"\nPresunto: "+usuario_lista_quadrante4[i].quantpresunto
                                                        +"\nManteiga: "+usuario_lista_quadrante4[i].quantmanteiga);
                                                ind_usuarios_horarios[j] = i;
                                                j++;
                                                l++;
                                            } else if (!bit_ocupado) {
                                                bit_ocupado = true;
                                                loc_endereco_usuario = usuario_lista_quadrante4[i].endereco.substring(0, index);
                                                lista_usuarios_horarios.add(usuario_lista_quadrante4[i].name + ", endereço:" + usuario_lista_quadrante4[i].endereco + ", referencia" + usuario_lista_quadrante4[i].referencia + ", horário entrega:" + usuario_lista_quadrante4[i].horario_entrega
                                                        + ", quantidade:\nPães: " + usuario_lista_quadrante4[i].quantpaes+"\nQueijo: "+usuario_lista_quadrante4[i].quantqueijo+"\nPresunto: "+usuario_lista_quadrante4[i].quantpresunto
                                                        +"\nManteiga: "+usuario_lista_quadrante4[i].quantmanteiga);
                                                ind_usuarios_horarios[j] = i;
                                                j++;
                                                l++;
                                            }
                                        } else {

                                        }
                                    } else if (!loc_endereco_usuario_historico.contains(usuario_lista_quadrante4[i].endereco.substring(0, index) + "@")){
                                        bit_ocupado = true;
                                        loc_endereco_usuario = usuario_lista_quadrante4[i].endereco.substring(0, index);
                                        lista_usuarios_horarios.add(usuario_lista_quadrante4[i].name + ", endereço:" + usuario_lista_quadrante4[i].endereco + ", referencia" + usuario_lista_quadrante4[i].referencia + ", horário entrega:" + usuario_lista_quadrante4[i].horario_entrega
                                                + ", quantidade:\nPães: " + usuario_lista_quadrante4[i].quantpaes+"\nQueijo: "+usuario_lista_quadrante4[i].quantqueijo+"\nPresunto: "+usuario_lista_quadrante4[i].quantpresunto
                                                +"\nManteiga: "+usuario_lista_quadrante4[i].quantmanteiga);
                                        ind_usuarios_horarios[j] = i;
                                        j++;
                                        l++;
                                    }
                                    k++;
                                }
                                if (i == d - 1) {
                                    bit_ocupado = false;
                                    loc_endereco_usuario_historico = loc_endereco_usuario_historico+","+loc_endereco_usuario+"@";
                                    loc_endereco_usuario = "";
                                    Log.e("Endereco"," -- "+loc_endereco_usuario_historico);
                                }
                            }
                        }
                        break;
                    case 5:
                        while (l < k) {
                            k=0;
                            for (int i = 0; i < e; i++) {
                                int index = usuario_lista_quadrante5[i].endereco.indexOf(",");
                                if (usuario_lista_quadrante5[i].horario_entrega.equals("8:40 às 8:50")) {
                                    if (!loc_endereco_usuario.isEmpty()) {
                                        if (!loc_endereco_usuario_historico.contains(usuario_lista_quadrante5[i].endereco.substring(0, index) + "@")) {
                                            if (loc_endereco_usuario.contains(usuario_lista_quadrante5[i].endereco.substring(0, index))) {
                                                lista_usuarios_horarios.add(usuario_lista_quadrante5[i].name + ", endereço:" + usuario_lista_quadrante5[i].endereco + ", referencia" + usuario_lista_quadrante5[i].referencia + ", horário entrega:" + usuario_lista_quadrante5[i].horario_entrega
                                                        + ", quantidade:\nPães: " + usuario_lista_quadrante5[i].quantpaes+"\nQueijo: "+usuario_lista_quadrante5[i].quantqueijo+"\nPresunto: "+usuario_lista_quadrante5[i].quantpresunto
                                                        +"\nManteiga: "+usuario_lista_quadrante5[i].quantmanteiga);
                                                ind_usuarios_horarios[j] = i;
                                                j++;
                                                l++;
                                            } else if (!bit_ocupado) {
                                                bit_ocupado = true;
                                                loc_endereco_usuario = usuario_lista_quadrante5[i].endereco.substring(0, index);
                                                lista_usuarios_horarios.add(usuario_lista_quadrante5[i].name + ", endereço:" + usuario_lista_quadrante5[i].endereco + ", referencia" + usuario_lista_quadrante5[i].referencia + ", horário entrega:" + usuario_lista_quadrante5[i].horario_entrega
                                                        + ", quantidade:\nPães: " + usuario_lista_quadrante5[i].quantpaes+"\nQueijo: "+usuario_lista_quadrante5[i].quantqueijo+"\nPresunto: "+usuario_lista_quadrante5[i].quantpresunto
                                                        +"\nManteiga: "+usuario_lista_quadrante5[i].quantmanteiga);
                                                ind_usuarios_horarios[j] = i;
                                                j++;
                                                l++;
                                            }
                                        } else {

                                        }
                                    } else if (!loc_endereco_usuario_historico.contains(usuario_lista_quadrante5[i].endereco.substring(0, index) + "@")){
                                        bit_ocupado = true;
                                        loc_endereco_usuario = usuario_lista_quadrante5[i].endereco.substring(0, index);
                                        lista_usuarios_horarios.add(usuario_lista_quadrante5[i].name + ", endereço:" + usuario_lista_quadrante5[i].endereco + ", referencia" + usuario_lista_quadrante5[i].referencia + ", horário entrega:" + usuario_lista_quadrante5[i].horario_entrega
                                                + ", quantidade:\nPães: " + usuario_lista_quadrante5[i].quantpaes+"\nQueijo: "+usuario_lista_quadrante5[i].quantqueijo+"\nPresunto: "+usuario_lista_quadrante5[i].quantpresunto
                                                +"\nManteiga: "+usuario_lista_quadrante5[i].quantmanteiga);
                                        ind_usuarios_horarios[j] = i;
                                        j++;
                                        l++;
                                    }
                                    k++;
                                }
                                if (i == e - 1) {
                                    bit_ocupado = false;
                                    loc_endereco_usuario_historico = loc_endereco_usuario_historico+","+loc_endereco_usuario+"@";
                                    loc_endereco_usuario = "";
                                    Log.e("Endereco"," -- "+loc_endereco_usuario_historico);
                                }
                            }
                        }
                        break;
                    case 6:
                        while (l < k) {
                            k=0;
                            for (int i = 0; i < f; i++) {
                                int index = usuario_lista_quadrante6[i].endereco.indexOf(",");
                                if (usuario_lista_quadrante6[i].horario_entrega.equals("8:50 às 9:00")) {
                                    if (!loc_endereco_usuario.isEmpty()) {
                                        if (!loc_endereco_usuario_historico.contains(usuario_lista_quadrante6[i].endereco.substring(0, index) + "@")) {
                                            if (loc_endereco_usuario.contains(usuario_lista_quadrante6[i].endereco.substring(0, index))) {
                                                lista_usuarios_horarios.add(usuario_lista_quadrante6[i].name + ", endereço:" + usuario_lista_quadrante6[i].endereco + ", referencia" + usuario_lista_quadrante6[i].referencia + ", horário entrega:" + usuario_lista_quadrante6[i].horario_entrega
                                                        + ", quantidade:\nPães: " + usuario_lista_quadrante6[i].quantpaes+"\nQueijo: "+usuario_lista_quadrante6[i].quantqueijo+"\nPresunto: "+usuario_lista_quadrante6[i].quantpresunto
                                                        +"\nManteiga: "+usuario_lista_quadrante6[i].quantmanteiga);
                                                ind_usuarios_horarios[j] = i;
                                                j++;
                                                l++;
                                            } else if (!bit_ocupado) {
                                                bit_ocupado = true;
                                                loc_endereco_usuario = usuario_lista_quadrante6[i].endereco.substring(0, index);
                                                lista_usuarios_horarios.add(usuario_lista_quadrante6[i].name + ", endereço:" + usuario_lista_quadrante6[i].endereco + ", referencia" + usuario_lista_quadrante6[i].referencia + ", horário entrega:" + usuario_lista_quadrante6[i].horario_entrega
                                                        + ", quantidade:\nPães: " + usuario_lista_quadrante6[i].quantpaes+"\nQueijo: "+usuario_lista_quadrante6[i].quantqueijo+"\nPresunto: "+usuario_lista_quadrante6[i].quantpresunto
                                                        +"\nManteiga: "+usuario_lista_quadrante6[i].quantmanteiga);
                                                ind_usuarios_horarios[j] = i;
                                                j++;
                                                l++;
                                            }
                                        } else {

                                        }
                                    } else if (!loc_endereco_usuario_historico.contains(usuario_lista_quadrante6[i].endereco.substring(0, index) + "@")){
                                        bit_ocupado = true;
                                        loc_endereco_usuario = usuario_lista_quadrante6[i].endereco.substring(0, index);
                                        lista_usuarios_horarios.add(usuario_lista_quadrante6[i].name + ", endereço:" + usuario_lista_quadrante6[i].endereco + ", referencia" + usuario_lista_quadrante6[i].referencia + ", horário entrega:" + usuario_lista_quadrante6[i].horario_entrega
                                                + ", quantidade:\nPães: " + usuario_lista_quadrante6[i].quantpaes+"\nQueijo: "+usuario_lista_quadrante6[i].quantqueijo+"\nPresunto: "+usuario_lista_quadrante6[i].quantpresunto
                                                +"\nManteiga: "+usuario_lista_quadrante6[i].quantmanteiga);
                                        ind_usuarios_horarios[j] = i;
                                        j++;
                                        l++;
                                    }
                                    k++;
                                }
                                if (i == f - 1) {
                                    bit_ocupado = false;
                                    loc_endereco_usuario_historico = loc_endereco_usuario_historico+","+loc_endereco_usuario+"@";
                                    loc_endereco_usuario = "";
                                    Log.e("Endereco"," -- "+loc_endereco_usuario_historico);
                                }
                            }
                        }
                        break;
                }
                adaptador_user_horario = new ArrayAdapter<String>(getApplicationContext(),R.layout.lista,lista_usuarios_horarios);
                listusuarios.setAdapter(adaptador_user_horario);
                listusuarios.setVisibility(View.VISIBLE);
            }
        });

        botao_horario4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                botao_horario1.setBackgroundColor(Color.LTGRAY);
                botao_horario2.setBackgroundColor(Color.LTGRAY);
                botao_horario3.setBackgroundColor(Color.LTGRAY);
                botao_horario4.setBackgroundColor(Color.YELLOW);
                botao_inicia_entrega.setEnabled(true);
                int j=0,k=1,l=0;
                String loc_endereco_usuario="",loc_endereco_usuario_historico="";
                boolean bit_ocupado = false;
                lista_usuarios_horarios.clear();
                switch (quadrante){
                    case 1:
                        while (l < k) {
                            k=0;
                            for (int i = 0; i <a; i++) {
                                int index = usuario_lista_quadrante1[i].endereco.indexOf(",");
                                if (usuario_lista_quadrante1[i].horario_entrega.equals("9:00 às 9:10")) {
                                    if (!loc_endereco_usuario.isEmpty()) {
                                        if (!loc_endereco_usuario_historico.contains(usuario_lista_quadrante1[i].endereco.substring(0, index) + "@")) {
                                            if (loc_endereco_usuario.contains(usuario_lista_quadrante1[i].endereco.substring(0, index))) {
                                                lista_usuarios_horarios.add(usuario_lista_quadrante1[i].name + ", endereço:" + usuario_lista_quadrante1[i].endereco + ", referencia" + usuario_lista_quadrante1[i].referencia + ", horário entrega:" + usuario_lista_quadrante1[i].horario_entrega
                                                        + ", quantidade:\nPães: " + usuario_lista_quadrante1[i].quantpaes+"\nQueijo: "+usuario_lista_quadrante1[i].quantqueijo+"\nPresunto: "+usuario_lista_quadrante1[i].quantpresunto
                                                        +"\nManteiga: "+usuario_lista_quadrante1[i].quantmanteiga);
                                                ind_usuarios_horarios[j] = i;
                                                j++;
                                                l++;
                                            } else if (!bit_ocupado) {
                                                bit_ocupado = true;
                                                loc_endereco_usuario = usuario_lista_quadrante1[i].endereco.substring(0, index);
                                                lista_usuarios_horarios.add(usuario_lista_quadrante1[i].name + ", endereço:" + usuario_lista_quadrante1[i].endereco + ", referencia" + usuario_lista_quadrante1[i].referencia + ", horário entrega:" + usuario_lista_quadrante1[i].horario_entrega
                                                        + ", quantidade:\nPães: " + usuario_lista_quadrante1[i].quantpaes+"\nQueijo: "+usuario_lista_quadrante1[i].quantqueijo+"\nPresunto: "+usuario_lista_quadrante1[i].quantpresunto
                                                        +"\nManteiga: "+usuario_lista_quadrante1[i].quantmanteiga);
                                                ind_usuarios_horarios[j] = i;
                                                j++;
                                                l++;
                                            }
                                        } else {

                                        }
                                    } else if (!loc_endereco_usuario_historico.contains(usuario_lista_quadrante1[i].endereco.substring(0, index) + "@")){
                                        bit_ocupado = true;
                                        loc_endereco_usuario = usuario_lista_quadrante1[i].endereco.substring(0, index);
                                        lista_usuarios_horarios.add(usuario_lista_quadrante1[i].name + ", endereço:" + usuario_lista_quadrante1[i].endereco + ", referencia" + usuario_lista_quadrante1[i].referencia + ", horário entrega:" + usuario_lista_quadrante1[i].horario_entrega
                                                + ", quantidade:\nPães: " + usuario_lista_quadrante1[i].quantpaes+"\nQueijo: "+usuario_lista_quadrante1[i].quantqueijo+"\nPresunto: "+usuario_lista_quadrante1[i].quantpresunto
                                                +"\nManteiga: "+usuario_lista_quadrante1[i].quantmanteiga);
                                        ind_usuarios_horarios[j] = i;
                                        j++;
                                        l++;
                                    }
                                    k++;
                                }
                                if (i == a - 1) {
                                    bit_ocupado = false;
                                    loc_endereco_usuario_historico = loc_endereco_usuario_historico+","+loc_endereco_usuario+"@";
                                    loc_endereco_usuario = "";
                                    Log.e("Endereco"," -- "+loc_endereco_usuario_historico);
                                }
                            }
                        }
                        break;
                    case 2:
                        while (l < k) {
                            k=0;
                            for (int i = 0; i < b; i++) {
                                int index = usuario_lista_quadrante2[i].endereco.indexOf(",");
                                if (usuario_lista_quadrante2[i].horario_entrega.equals("9:10 às 9:20")) {
                                    if (!loc_endereco_usuario.isEmpty()) {
                                        if (!loc_endereco_usuario_historico.contains(usuario_lista_quadrante2[i].endereco.substring(0, index) + "@")) {
                                            if (loc_endereco_usuario.contains(usuario_lista_quadrante2[i].endereco.substring(0, index))) {
                                                lista_usuarios_horarios.add(usuario_lista_quadrante2[i].name + ", endereço:" + usuario_lista_quadrante2[i].endereco + ", referencia" + usuario_lista_quadrante2[i].referencia + ", horário entrega:" + usuario_lista_quadrante2[i].horario_entrega
                                                        + ", quantidade:\nPães: " + usuario_lista_quadrante2[i].quantpaes+"\nQueijo: "+usuario_lista_quadrante2[i].quantqueijo+"\nPresunto: "+usuario_lista_quadrante2[i].quantpresunto
                                                        +"\nManteiga: "+usuario_lista_quadrante2[i].quantmanteiga);
                                                ind_usuarios_horarios[j] = i;
                                                j++;
                                                l++;
                                            } else if (!bit_ocupado) {
                                                bit_ocupado = true;
                                                loc_endereco_usuario = usuario_lista_quadrante2[i].endereco.substring(0, index);
                                                lista_usuarios_horarios.add(usuario_lista_quadrante2[i].name + ", endereço:" + usuario_lista_quadrante2[i].endereco + ", referencia" + usuario_lista_quadrante2[i].referencia + ", horário entrega:" + usuario_lista_quadrante2[i].horario_entrega
                                                        + ", quantidade:\nPães: " + usuario_lista_quadrante2[i].quantpaes+"\nQueijo: "+usuario_lista_quadrante2[i].quantqueijo+"\nPresunto: "+usuario_lista_quadrante2[i].quantpresunto
                                                        +"\nManteiga: "+usuario_lista_quadrante2[i].quantmanteiga);
                                                ind_usuarios_horarios[j] = i;
                                                j++;
                                                l++;
                                            }
                                        } else {

                                        }
                                    } else if (!loc_endereco_usuario_historico.contains(usuario_lista_quadrante2[i].endereco.substring(0, index) + "@")){
                                        bit_ocupado = true;
                                        loc_endereco_usuario = usuario_lista_quadrante2[i].endereco.substring(0, index);
                                        lista_usuarios_horarios.add(usuario_lista_quadrante2[i].name + ", endereço:" + usuario_lista_quadrante2[i].endereco + ", referencia" + usuario_lista_quadrante2[i].referencia + ", horário entrega:" + usuario_lista_quadrante2[i].horario_entrega
                                                + ", quantidade:\nPães: " + usuario_lista_quadrante2[i].quantpaes+"\nQueijo: "+usuario_lista_quadrante2[i].quantqueijo+"\nPresunto: "+usuario_lista_quadrante2[i].quantpresunto
                                                +"\nManteiga: "+usuario_lista_quadrante2[i].quantmanteiga);
                                        ind_usuarios_horarios[j] = i;
                                        j++;
                                        l++;
                                    }
                                    k++;
                                }
                                if (i == b - 1) {
                                    bit_ocupado = false;
                                    loc_endereco_usuario_historico = loc_endereco_usuario_historico+","+loc_endereco_usuario+"@";
                                    loc_endereco_usuario = "";
                                    Log.e("Endereco"," -- "+loc_endereco_usuario_historico);
                                }
                            }
                        }
                        break;
                    case 3:
                        while (l < k) {
                            k=0;
                            for (int i = 0; i < c; i++) {
                                int index = usuario_lista_quadrante3[i].endereco.indexOf(",");
                                if (usuario_lista_quadrante3[i].horario_entrega.equals("9:20 às 9:30")) {
                                    if (!loc_endereco_usuario.isEmpty()) {
                                        if (!loc_endereco_usuario_historico.contains(usuario_lista_quadrante3[i].endereco.substring(0, index) + "@")) {
                                            if (loc_endereco_usuario.contains(usuario_lista_quadrante3[i].endereco.substring(0, index))) {
                                                lista_usuarios_horarios.add(usuario_lista_quadrante3[i].name + ", endereço:" + usuario_lista_quadrante3[i].endereco + ", referencia" + usuario_lista_quadrante3[i].referencia + ", horário entrega:" + usuario_lista_quadrante3[i].horario_entrega
                                                        + ", quantidade:\nPães: " + usuario_lista_quadrante3[i].quantpaes+"\nQueijo: "+usuario_lista_quadrante3[i].quantqueijo+"\nPresunto: "+usuario_lista_quadrante3[i].quantpresunto
                                                        +"\nManteiga: "+usuario_lista_quadrante3[i].quantmanteiga);
                                                ind_usuarios_horarios[j] = i;
                                                j++;
                                                l++;
                                            } else if (!bit_ocupado) {
                                                bit_ocupado = true;
                                                loc_endereco_usuario = usuario_lista_quadrante3[i].endereco.substring(0, index);
                                                lista_usuarios_horarios.add(usuario_lista_quadrante3[i].name + ", endereço:" + usuario_lista_quadrante3[i].endereco + ", referencia" + usuario_lista_quadrante3[i].referencia + ", horário entrega:" + usuario_lista_quadrante3[i].horario_entrega
                                                        + ", quantidade:\nPães: " + usuario_lista_quadrante3[i].quantpaes+"\nQueijo: "+usuario_lista_quadrante3[i].quantqueijo+"\nPresunto: "+usuario_lista_quadrante3[i].quantpresunto
                                                        +"\nManteiga: "+usuario_lista_quadrante3[i].quantmanteiga);
                                                ind_usuarios_horarios[j] = i;
                                                j++;
                                                l++;
                                            }
                                        } else {

                                        }
                                    } else if (!loc_endereco_usuario_historico.contains(usuario_lista_quadrante3[i].endereco.substring(0, index) + "@")){
                                        bit_ocupado = true;
                                        loc_endereco_usuario = usuario_lista_quadrante3[i].endereco.substring(0, index);
                                        lista_usuarios_horarios.add(usuario_lista_quadrante3[i].name + ", endereço:" + usuario_lista_quadrante3[i].endereco + ", referencia" + usuario_lista_quadrante3[i].referencia + ", horário entrega:" + usuario_lista_quadrante3[i].horario_entrega
                                                + ", quantidade:\nPães: " + usuario_lista_quadrante3[i].quantpaes+"\nQueijo: "+usuario_lista_quadrante3[i].quantqueijo+"\nPresunto: "+usuario_lista_quadrante3[i].quantpresunto
                                                +"\nManteiga: "+usuario_lista_quadrante3[i].quantmanteiga);
                                        ind_usuarios_horarios[j] = i;
                                        j++;
                                        l++;
                                    }
                                    k++;
                                }
                                if (i == c - 1) {
                                    bit_ocupado = false;
                                    loc_endereco_usuario_historico = loc_endereco_usuario_historico+","+loc_endereco_usuario+"@";
                                    loc_endereco_usuario = "";
                                    Log.e("Endereco"," -- "+loc_endereco_usuario_historico);
                                }
                            }
                        }
                        break;
                    case 4:
                        while (l < k) {
                            k=0;
                            for (int i = 0; i < d; i++) {
                                int index = usuario_lista_quadrante4[i].endereco.indexOf(",");
                                if (usuario_lista_quadrante4[i].horario_entrega.equals("9:30 às 9:40")) {
                                    if (!loc_endereco_usuario.isEmpty()) {
                                        if (!loc_endereco_usuario_historico.contains(usuario_lista_quadrante4[i].endereco.substring(0, index) + "@")) {
                                            if (loc_endereco_usuario.contains(usuario_lista_quadrante4[i].endereco.substring(0, index))) {
                                                lista_usuarios_horarios.add(usuario_lista_quadrante4[i].name + ", endereço:" + usuario_lista_quadrante4[i].endereco + ", referencia" + usuario_lista_quadrante4[i].referencia + ", horário entrega:" + usuario_lista_quadrante4[i].horario_entrega
                                                        + ", quantidade:\nPães: " + usuario_lista_quadrante4[i].quantpaes+"\nQueijo: "+usuario_lista_quadrante4[i].quantqueijo+"\nPresunto: "+usuario_lista_quadrante4[i].quantpresunto
                                                        +"\nManteiga: "+usuario_lista_quadrante4[i].quantmanteiga);
                                                ind_usuarios_horarios[j] = i;
                                                j++;
                                                l++;
                                            } else if (!bit_ocupado) {
                                                bit_ocupado = true;
                                                loc_endereco_usuario = usuario_lista_quadrante4[i].endereco.substring(0, index);
                                                lista_usuarios_horarios.add(usuario_lista_quadrante4[i].name + ", endereço:" + usuario_lista_quadrante4[i].endereco + ", referencia" + usuario_lista_quadrante4[i].referencia + ", horário entrega:" + usuario_lista_quadrante4[i].horario_entrega
                                                        + ", quantidade:\nPães: " + usuario_lista_quadrante4[i].quantpaes+"\nQueijo: "+usuario_lista_quadrante4[i].quantqueijo+"\nPresunto: "+usuario_lista_quadrante4[i].quantpresunto
                                                        +"\nManteiga: "+usuario_lista_quadrante4[i].quantmanteiga);
                                                ind_usuarios_horarios[j] = i;
                                                j++;
                                                l++;
                                            }
                                        } else {

                                        }
                                    } else if (!loc_endereco_usuario_historico.contains(usuario_lista_quadrante4[i].endereco.substring(0, index) + "@")){
                                        bit_ocupado = true;
                                        loc_endereco_usuario = usuario_lista_quadrante4[i].endereco.substring(0, index);
                                        lista_usuarios_horarios.add(usuario_lista_quadrante4[i].name + ", endereço:" + usuario_lista_quadrante4[i].endereco + ", referencia" + usuario_lista_quadrante4[i].referencia + ", horário entrega:" + usuario_lista_quadrante4[i].horario_entrega
                                                + ", quantidade:\nPães: " + usuario_lista_quadrante4[i].quantpaes+"\nQueijo: "+usuario_lista_quadrante4[i].quantqueijo+"\nPresunto: "+usuario_lista_quadrante4[i].quantpresunto
                                                +"\nManteiga: "+usuario_lista_quadrante4[i].quantmanteiga);
                                        ind_usuarios_horarios[j] = i;
                                        j++;
                                        l++;
                                    }
                                    k++;
                                }
                                if (i == d - 1) {
                                    bit_ocupado = false;
                                    loc_endereco_usuario_historico = loc_endereco_usuario_historico+","+loc_endereco_usuario+"@";
                                    loc_endereco_usuario = "";
                                    Log.e("Endereco"," -- "+loc_endereco_usuario_historico);
                                }
                            }
                        }
                        break;
                    case 5:
                        while (l < k) {
                            k=0;
                            for (int i = 0; i < e; i++) {
                                int index = usuario_lista_quadrante5[i].endereco.indexOf(",");
                                if (usuario_lista_quadrante5[i].horario_entrega.equals("9:40 às 9:50")) {
                                    if (!loc_endereco_usuario.isEmpty()) {
                                        if (!loc_endereco_usuario_historico.contains(usuario_lista_quadrante5[i].endereco.substring(0, index) + "@")) {
                                            if (loc_endereco_usuario.contains(usuario_lista_quadrante5[i].endereco.substring(0, index))) {
                                                lista_usuarios_horarios.add(usuario_lista_quadrante5[i].name + ", endereço:" + usuario_lista_quadrante5[i].endereco + ", referencia" + usuario_lista_quadrante5[i].referencia + ", horário entrega:" + usuario_lista_quadrante5[i].horario_entrega
                                                        + ", quantidade:\nPães: " + usuario_lista_quadrante5[i].quantpaes+"\nQueijo: "+usuario_lista_quadrante5[i].quantqueijo+"\nPresunto: "+usuario_lista_quadrante5[i].quantpresunto
                                                        +"\nManteiga: "+usuario_lista_quadrante5[i].quantmanteiga);
                                                ind_usuarios_horarios[j] = i;
                                                j++;
                                                l++;
                                            } else if (!bit_ocupado) {
                                                bit_ocupado = true;
                                                loc_endereco_usuario = usuario_lista_quadrante5[i].endereco.substring(0, index);
                                                lista_usuarios_horarios.add(usuario_lista_quadrante5[i].name + ", endereço:" + usuario_lista_quadrante5[i].endereco + ", referencia" + usuario_lista_quadrante5[i].referencia + ", horário entrega:" + usuario_lista_quadrante5[i].horario_entrega
                                                        + ", quantidade:\nPães: " + usuario_lista_quadrante5[i].quantpaes+"\nQueijo: "+usuario_lista_quadrante5[i].quantqueijo+"\nPresunto: "+usuario_lista_quadrante5[i].quantpresunto
                                                        +"\nManteiga: "+usuario_lista_quadrante5[i].quantmanteiga);
                                                ind_usuarios_horarios[j] = i;
                                                j++;
                                                l++;
                                            }
                                        } else {

                                        }
                                    } else if (!loc_endereco_usuario_historico.contains(usuario_lista_quadrante5[i].endereco.substring(0, index) + "@")){
                                        bit_ocupado = true;
                                        loc_endereco_usuario = usuario_lista_quadrante5[i].endereco.substring(0, index);
                                        lista_usuarios_horarios.add(usuario_lista_quadrante5[i].name + ", endereço:" + usuario_lista_quadrante5[i].endereco + ", referencia" + usuario_lista_quadrante5[i].referencia + ", horário entrega:" + usuario_lista_quadrante5[i].horario_entrega
                                                + ", quantidade:\nPães: " + usuario_lista_quadrante5[i].quantpaes+"\nQueijo: "+usuario_lista_quadrante5[i].quantqueijo+"\nPresunto: "+usuario_lista_quadrante5[i].quantpresunto
                                                +"\nManteiga: "+usuario_lista_quadrante5[i].quantmanteiga);
                                        ind_usuarios_horarios[j] = i;
                                        j++;
                                        l++;
                                    }
                                    k++;
                                }
                                if (i == e - 1) {
                                    bit_ocupado = false;
                                    loc_endereco_usuario_historico = loc_endereco_usuario_historico+","+loc_endereco_usuario+"@";
                                    loc_endereco_usuario = "";
                                    Log.e("Endereco"," -- "+loc_endereco_usuario_historico);
                                }
                            }
                        }
                        break;
                    case 6:
                        while (l < k) {
                            k=0;
                            for (int i = 0; i < f; i++) {
                                int index = usuario_lista_quadrante6[i].endereco.indexOf(",");
                                if (usuario_lista_quadrante6[i].horario_entrega.equals("9:50 às 10:00")) {
                                    if (!loc_endereco_usuario.isEmpty()) {
                                        if (!loc_endereco_usuario_historico.contains(usuario_lista_quadrante6[i].endereco.substring(0, index) + "@")) {
                                            if (loc_endereco_usuario.contains(usuario_lista_quadrante6[i].endereco.substring(0, index))) {
                                                lista_usuarios_horarios.add(usuario_lista_quadrante6[i].name + ", endereço:" + usuario_lista_quadrante6[i].endereco + ", referencia" + usuario_lista_quadrante6[i].referencia + ", horário entrega:" + usuario_lista_quadrante6[i].horario_entrega
                                                        + ", quantidade:\nPães: " + usuario_lista_quadrante6[i].quantpaes+"\nQueijo: "+usuario_lista_quadrante6[i].quantqueijo+"\nPresunto: "+usuario_lista_quadrante6[i].quantpresunto
                                                        +"\nManteiga: "+usuario_lista_quadrante6[i].quantmanteiga);
                                                ind_usuarios_horarios[j] = i;
                                                j++;
                                                l++;
                                            } else if (!bit_ocupado) {
                                                bit_ocupado = true;
                                                loc_endereco_usuario = usuario_lista_quadrante6[i].endereco.substring(0, index);
                                                lista_usuarios_horarios.add(usuario_lista_quadrante6[i].name + ", endereço:" + usuario_lista_quadrante6[i].endereco + ", referencia" + usuario_lista_quadrante6[i].referencia + ", horário entrega:" + usuario_lista_quadrante6[i].horario_entrega
                                                        + ", quantidade:\nPães: " + usuario_lista_quadrante6[i].quantpaes+"\nQueijo: "+usuario_lista_quadrante6[i].quantqueijo+"\nPresunto: "+usuario_lista_quadrante6[i].quantpresunto
                                                        +"\nManteiga: "+usuario_lista_quadrante6[i].quantmanteiga);
                                                ind_usuarios_horarios[j] = i;
                                                j++;
                                                l++;
                                            }
                                        } else {

                                        }
                                    } else if (!loc_endereco_usuario_historico.contains(usuario_lista_quadrante6[i].endereco.substring(0, index) + "@")){
                                        bit_ocupado = true;
                                        loc_endereco_usuario = usuario_lista_quadrante6[i].endereco.substring(0, index);
                                        lista_usuarios_horarios.add(usuario_lista_quadrante6[i].name + ", endereço:" + usuario_lista_quadrante6[i].endereco + ", referencia" + usuario_lista_quadrante6[i].referencia + ", horário entrega:" + usuario_lista_quadrante6[i].horario_entrega
                                                + ", quantidade:\nPães: " + usuario_lista_quadrante6[i].quantpaes+"\nQueijo: "+usuario_lista_quadrante6[i].quantqueijo+"\nPresunto: "+usuario_lista_quadrante6[i].quantpresunto
                                                +"\nManteiga: "+usuario_lista_quadrante6[i].quantmanteiga);
                                        ind_usuarios_horarios[j] = i;
                                        j++;
                                        l++;
                                    }
                                    k++;
                                }
                                if (i == f - 1) {
                                    bit_ocupado = false;
                                    loc_endereco_usuario_historico = loc_endereco_usuario_historico+","+loc_endereco_usuario+"@";
                                    loc_endereco_usuario = "";
                                    Log.e("Endereco"," -- "+loc_endereco_usuario_historico);
                                }
                            }
                        }
                        break;
                }
                adaptador_user_horario = new ArrayAdapter<String>(getApplicationContext(),R.layout.lista,lista_usuarios_horarios);
                listusuarios.setAdapter(adaptador_user_horario);
                listusuarios.setVisibility(View.VISIBLE);
            }
        });

        listusuarios.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (listusuarios.getAdapter().equals(adaptador_user_horario)) {
                    switch (quadrante){
                        case 1:
                            switch (position) {
                                case 0:
                                    Log.e("TOTAL PAES",": "+total_paes);
                                    Log.e("TOTAL PAES Entregues",": "+total_paes_entregue);
                                    myref.child("usuarios").child(usuario_lista_quadrante1[ind_usuarios_horarios[0]].phone).child("pedido").setValue("false");
                                    total_paes_entregue += usuario_lista_quadrante1[ind_usuarios_horarios[0]].quantpaes;
                                    total_queijo_entregue += usuario_lista_quadrante1[ind_usuarios_horarios[0]].quantqueijo;
                                    total_presunto_entregue += usuario_lista_quadrante1[ind_usuarios_horarios[0]].quantpresunto;
                                    total_manteiga_entregue += usuario_lista_quadrante1[ind_usuarios_horarios[0]].quantmanteiga;
                                    entregas_feitas++;
                                    textView_quant_pendente_pao.setText(String.valueOf(-1*(total_paes - total_paes_entregue)));
                                    textView_quant_pendente_queijo.setText(String.valueOf(-1*(total_queijo - total_queijo_entregue)));
                                    textView_quant_pendente_presunto.setText(String.valueOf(-1*(total_presunto - total_presunto_entregue)));
                                    textView_quant_pendente_manteiga.setText(String.valueOf(-1*(total_manteiga - total_manteiga_entregue)));
                                    textView_total_entregas_feitas.setText("Entregas feitas: "+entregas_feitas);
                                    if (ind_usuarios_horarios[1] != null)
                                        myref.child("usuarios").child(usuario_lista_quadrante1[ind_usuarios_horarios[1]].phone).child("pedido").setValue("quase");

                                    lista_usuarios_horarios.remove(0);
                                    for (int j=0;j<=98;j++){
                                        ind_usuarios_horarios[j] = ind_usuarios_horarios[j+1];
                                    }
                                    adaptador_user_horario.notifyDataSetChanged();
                                    break;
                            }
                            break;

                        case 2:
                            switch (position) {
                                case 0:
                                    myref.child("usuarios").child(usuario_lista_quadrante2[ind_usuarios_horarios[0]].phone).child("pedido").setValue("false");
                                    total_paes_entregue += usuario_lista_quadrante2[ind_usuarios_horarios[0]].quantpaes;
                                    total_queijo_entregue += usuario_lista_quadrante2[ind_usuarios_horarios[0]].quantqueijo;
                                    total_presunto_entregue += usuario_lista_quadrante2[ind_usuarios_horarios[0]].quantpresunto;
                                    total_manteiga_entregue += usuario_lista_quadrante2[ind_usuarios_horarios[0]].quantmanteiga;
                                    entregas_feitas++;
                                    textView_quant_pendente_pao.setText(String.valueOf(-1*(total_paes - total_paes_entregue)));
                                    textView_quant_pendente_queijo.setText(String.valueOf(-1*(total_queijo - total_queijo_entregue)));
                                    textView_quant_pendente_presunto.setText(String.valueOf(-1*(total_presunto - total_presunto_entregue)));
                                    textView_quant_pendente_manteiga.setText(String.valueOf(-1*(total_manteiga - total_manteiga_entregue)));
                                    textView_total_entregas_feitas.setText("Entregas feitas: "+entregas_feitas);
                                    if (ind_usuarios_horarios[1] != null)
                                        myref.child("usuarios").child(usuario_lista_quadrante2[ind_usuarios_horarios[1]].phone).child("pedido").setValue("quase");
                                    lista_usuarios_horarios.remove(0);
                                    for (int j = 0; j <= 98; j++) {
                                        ind_usuarios_horarios[j] = ind_usuarios_horarios[j + 1];
                                    }
                                    adaptador_user_horario.notifyDataSetChanged();
                                    break;
                            }
                            break;

                        case 3:
                            switch (position) {
                                case 0:
                                    myref.child("usuarios").child(usuario_lista_quadrante3[ind_usuarios_horarios[0]].phone).child("pedido").setValue("false");
                                    total_paes_entregue += usuario_lista_quadrante3[ind_usuarios_horarios[0]].quantpaes;
                                    total_queijo_entregue += usuario_lista_quadrante3[ind_usuarios_horarios[0]].quantqueijo;
                                    total_presunto_entregue += usuario_lista_quadrante3[ind_usuarios_horarios[0]].quantpresunto;
                                    total_manteiga_entregue += usuario_lista_quadrante3[ind_usuarios_horarios[0]].quantmanteiga;
                                    entregas_feitas++;
                                    textView_quant_pendente_pao.setText(String.valueOf(-1*(total_paes - total_paes_entregue)));
                                    textView_quant_pendente_queijo.setText(String.valueOf(-1*(total_queijo - total_queijo_entregue)));
                                    textView_quant_pendente_presunto.setText(String.valueOf(-1*(total_presunto - total_presunto_entregue)));
                                    textView_quant_pendente_manteiga.setText(String.valueOf(-1*(total_manteiga - total_manteiga_entregue)));
                                    textView_total_entregas_feitas.setText("Entregas feitas: "+entregas_feitas);
                                    if (ind_usuarios_horarios[1] != null)
                                        myref.child("usuarios").child(usuario_lista_quadrante3[ind_usuarios_horarios[1]].phone).child("pedido").setValue("quase");
                                    lista_usuarios_horarios.remove(0);
                                    for (int j = 0; j <= 98; j++) {
                                        ind_usuarios_horarios[j] = ind_usuarios_horarios[j + 1];
                                    }
                                    adaptador_user_horario.notifyDataSetChanged();
                                    break;
                            }
                            break;

                        case 4:
                            switch (position) {
                                case 0:
                                    myref.child("usuarios").child(usuario_lista_quadrante4[ind_usuarios_horarios[0]].phone).child("pedido").setValue("false");
                                    total_paes_entregue += usuario_lista_quadrante4[ind_usuarios_horarios[0]].quantpaes;
                                    total_queijo_entregue += usuario_lista_quadrante4[ind_usuarios_horarios[0]].quantqueijo;
                                    total_presunto_entregue += usuario_lista_quadrante4[ind_usuarios_horarios[0]].quantpresunto;
                                    total_manteiga_entregue += usuario_lista_quadrante4[ind_usuarios_horarios[0]].quantmanteiga;
                                    entregas_feitas++;
                                    textView_quant_pendente_pao.setText(String.valueOf(-1*(total_paes - total_paes_entregue)));
                                    textView_quant_pendente_queijo.setText(String.valueOf(-1*(total_queijo - total_queijo_entregue)));
                                    textView_quant_pendente_presunto.setText(String.valueOf(-1*(total_presunto - total_presunto_entregue)));
                                    textView_quant_pendente_manteiga.setText(String.valueOf(-1*(total_manteiga - total_manteiga_entregue)));
                                    textView_total_entregas_feitas.setText("Entregas feitas: "+entregas_feitas);
                                    Log.e("Lista usuarios -- ",""+lista_usuarios4.get(ind_usuarios_horarios[0]));
                                    Log.e("USU -- ",""+ind_usuarios_horarios[0]);
                                    if (ind_usuarios_horarios[1] != null)
                                        myref.child("usuarios").child(usuario_lista_quadrante4[ind_usuarios_horarios[1]].phone).child("pedido").setValue("quase");
                                    lista_usuarios_horarios.remove(0);
                                    for (int j = 0; j <= 98; j++) {
                                        ind_usuarios_horarios[j] = ind_usuarios_horarios[j + 1];
                                    }
                                    adaptador_user_horario.notifyDataSetChanged();
                                    break;
                            }
                            break;

                        case 5:
                            switch (position) {
                                case 0:
                                    myref.child("usuarios").child(usuario_lista_quadrante5[ind_usuarios_horarios[0]].phone).child("pedido").setValue("false");
                                    total_paes_entregue += usuario_lista_quadrante5[ind_usuarios_horarios[0]].quantpaes;
                                    total_queijo_entregue += usuario_lista_quadrante5[ind_usuarios_horarios[0]].quantqueijo;
                                    total_presunto_entregue += usuario_lista_quadrante5[ind_usuarios_horarios[0]].quantpresunto;
                                    total_manteiga_entregue += usuario_lista_quadrante5[ind_usuarios_horarios[0]].quantmanteiga;
                                    entregas_feitas++;
                                    textView_quant_pendente_pao.setText(String.valueOf(-1*(total_paes - total_paes_entregue)));
                                    textView_quant_pendente_queijo.setText(String.valueOf(-1*(total_queijo - total_queijo_entregue)));
                                    textView_quant_pendente_presunto.setText(String.valueOf(-1*(total_presunto - total_presunto_entregue)));
                                    textView_quant_pendente_manteiga.setText(String.valueOf(-1*(total_manteiga - total_manteiga_entregue)));
                                    textView_total_entregas_feitas.setText("Entregas feitas: "+entregas_feitas);
                                    if (ind_usuarios_horarios[1] != null)
                                        myref.child("usuarios").child(usuario_lista_quadrante5[ind_usuarios_horarios[1]].phone).child("pedido").setValue("quase");
                                    lista_usuarios_horarios.remove(0);
                                    for (int j = 0; j <= 98; j++) {
                                        ind_usuarios_horarios[j] = ind_usuarios_horarios[j + 1];
                                    }
                                    adaptador_user_horario.notifyDataSetChanged();
                                    break;
                            }
                            break;

                        case 6:
                            switch (position) {
                                case 0:
                                    myref.child("usuarios").child(usuario_lista_quadrante6[ind_usuarios_horarios[0]].phone).child("pedido").setValue("false");
                                    total_paes_entregue += usuario_lista_quadrante6[ind_usuarios_horarios[0]].quantpaes;
                                    total_queijo_entregue += usuario_lista_quadrante6[ind_usuarios_horarios[0]].quantqueijo;
                                    total_presunto_entregue += usuario_lista_quadrante6[ind_usuarios_horarios[0]].quantpresunto;
                                    total_manteiga_entregue += usuario_lista_quadrante6[ind_usuarios_horarios[0]].quantmanteiga;
                                    entregas_feitas++;
                                    textView_quant_pendente_pao.setText(String.valueOf(-1*(total_paes - total_paes_entregue)));
                                    textView_quant_pendente_queijo.setText(String.valueOf(-1*(total_queijo - total_queijo_entregue)));
                                    textView_quant_pendente_presunto.setText(String.valueOf(-1*(total_presunto - total_presunto_entregue)));
                                    textView_quant_pendente_manteiga.setText(String.valueOf(-1*(total_manteiga - total_manteiga_entregue)));
                                    textView_total_entregas_feitas.setText("Entregas feitas: "+entregas_feitas);
                                    if (ind_usuarios_horarios[1] != null)
                                        myref.child("usuarios").child(usuario_lista_quadrante6[ind_usuarios_horarios[1]].phone).child("pedido").setValue("quase");
                                    lista_usuarios_horarios.remove(0);
                                    for (int j = 0; j <= 98; j++) {
                                        ind_usuarios_horarios[j] = ind_usuarios_horarios[j + 1];
                                    }
                                    adaptador_user_horario.notifyDataSetChanged();
                                    break;
                            }
                            break;
                    }
                }else {
                    Toast.makeText(getApplicationContext(),"Selecione um horário",Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
