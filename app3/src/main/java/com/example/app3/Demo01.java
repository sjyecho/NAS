package com.example.app3;

import androidx.annotation.NonNull;

import com.example.app3.bean.Repo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
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

        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create(gson))//这条转换语句需要依赖才能执行
                //.client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//RxJava配合Observable使用
                .build();

        GithubService service = retrofit.create(GithubService.class);

        /*
            发送请求的代码是这一句
            返回的repos其实并不是真正的数据结果,它像一条指令
         */
        Call<Repo> repos = service.listRepos("sjyecho");

//        repos.subscribeOn(Schedulers.io()).subscribe(new Observer<Repo>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//
//            }
//
//            @Override
//            public void onNext(Repo repo) {
//                System.out.println(repo.toString());
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                e.printStackTrace();
//            }
//
//            @Override
//            public void onComplete() {
//                System.out.println("success");
//            }
//        });


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

        @GET("users/{user}")
        Observable<Repo> getListRepos(@Path("user") String user);
    }
}