package com.parso.pruebaparsoandroid.Activities;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.parso.pruebaparsoandroid.Adapters.UserAdapter;
import com.parso.pruebaparsoandroid.Adapters.UserLocalAdapter;
import com.parso.pruebaparsoandroid.Models.UserList;
import com.parso.pruebaparsoandroid.Models.UserLocal;
import com.parso.pruebaparsoandroid.R;
import com.parso.pruebaparsoandroid.SQLite.ParsoSQLiteOpenHelper;
import com.parso.pruebaparsoandroid.Services.APIService;
import com.parso.pruebaparsoandroid.Services.UserService;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsersActivity extends AppCompatActivity {

    private static final UserService service =  APIService.getApi().create(UserService.class);
    private ProgressDialog progressDialog;
    private RecyclerView rv;
    //private UserAdapter userAdapter;
    private UserLocalAdapter userLocalAdapter;
    private LinearLayoutManager llmUser = new LinearLayoutManager(this);
    ContentValues users = new ContentValues();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        rv = (RecyclerView)findViewById(R.id.rv_user);

        createList();
    }

    public static byte[] getPictureByteOfArray(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public static Bitmap getBitmapFromByte(byte[] image) {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }

    private void createList() {
        progressDialog = ProgressDialog.show(
                UsersActivity.this,
                "",
                "Cargando datos...",
                true);
        llmUser = new LinearLayoutManager(this);
        rv.setLayoutManager(llmUser);

        service.getUsers().enqueue(new Callback<UserList>() {
            @Override
            public void onResponse(Call<UserList> call, Response<UserList> response) {
                final UserList lu = response.body();
                final List<UserLocal> u = new ArrayList<UserLocal>();

                ParsoSQLiteOpenHelper p = new ParsoSQLiteOpenHelper(UsersActivity.this,"ParsoDB", null, 1);

                final SQLiteDatabase bd = p.getWritableDatabase();

                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try  {
                            for(int i = 0; i < lu.getUser().size() - 1; i++) {
                                users.put("name", lu.getUser().get(i).getFullName());
                                users.put("email", lu.getUser().get(i).getEmail());

                                InputStream inputStream = new URL(lu.getUser().get(i).getPicture().getLarge()).openStream();
                                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                                users.put("picture", getPictureByteOfArray(bitmap));

                                bd.insert("usersTbl", null, users);
                            }

                            bd.close();

                            ParsoSQLiteOpenHelper admin = new ParsoSQLiteOpenHelper(UsersActivity.this,"ParsoDB", null, 1);
                            SQLiteDatabase bd1 = admin.getWritableDatabase();

                            String[] fields = new String[] {"name, email, picture"};

                            Cursor fila = bd1.query  ("usersTbl", fields, null, null, null, null, null);
                            if (fila.moveToFirst()) {
                                do {
                                    UserLocal user = new UserLocal(fila.getString(0), fila.getString(1), getBitmapFromByte(fila.getBlob(fila.getColumnIndex("picture"))));
                                    u.add(user);
                                } while (fila.moveToNext());
                            }

                            bd1.close();

                            runOnUiThread(new Runnable() {
                                public void run() {
                                    userLocalAdapter = new UserLocalAdapter(u);
                                    rv.setAdapter(userLocalAdapter);
                                }

                            });

                            progressDialog.dismiss();

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });

                thread.start();
            }

            @Override
            public void onFailure(Call<UserList> call, Throwable t) {
                Toast.makeText(UsersActivity.this, t.getMessage(),Toast.LENGTH_LONG).show();
                progressDialog.dismiss();
            }
        });
    }
}
