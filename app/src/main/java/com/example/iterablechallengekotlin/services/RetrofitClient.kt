import com.example.iterablechallengekotlin.services.WorkoutService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    val workoutService: WorkoutService by lazy {
        Retrofit.Builder()
            .baseUrl("https://iterable-assignment.vercel.app/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WorkoutService::class.java)
    }
}