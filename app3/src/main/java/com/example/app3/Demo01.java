package com.example.app3;

import androidx.annotation.NonNull;
import com.example.app3.bean.Repo;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Retrofit网络请求框架案例
 */
public class Demo01 {

    public static void main(String[] args) {

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())//这条转换语句需要依赖才能执行
                .client(okHttpClient)
                .build();

        GithubService service = retrofit.create(GithubService.class);

        /*
            发送请求的代码是这一句
            返回的repos其实并不是真正的数据结果,它像一条指令
         */
        Call<Repo> repos = service.listRepos("sjyecho");

        //异步请求
        repos.enqueue(new Callback<Repo>() {
            @Override
            public void onResponse(@NonNull Call<Repo> call, @NonNull Response<Repo> response) {
                Repo data = response.body();
                System.out.println(data);
            }

            @Override
            public void onFailure(@NonNull Call<Repo> call, @NonNull Throwable t) {
                t.printStackTrace();
            }
        });

        //同步请求
//        try {
//            Response<Repo> execute = repos.execute();
//            String s = execute.body().toString();
//            System.out.println(s);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    interface GithubService {
        @GET("users/{user}")
        Call<Repo> listRepos(@Path("user") String user);
    }
}