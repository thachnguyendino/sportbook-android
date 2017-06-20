package com.dinosys.sportbook.features.tournament.signup

import android.content.Context
import com.dinosys.sportbook.extensions.readJsonFromAsset
import com.dinosys.sportbook.networks.models.SkillModel
import com.dinosys.sportbook.networks.tournament.TournamentAPI
import io.reactivex.Observable
import org.json.JSONArray
import org.json.JSONException
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class TournamentSignUpViewModel @Inject constructor(val tournamentAPI: TournamentAPI) {

    fun getSkills() : Observable<Response<SkillModel>> {
        return tournamentAPI.getSkills()
    }

    fun getCities(context: Context): Observable<JSONArray>  = Observable.create<JSONArray> {
            e ->
            try {
                val jsonText = String.readJsonFromAsset(context, "list_of_cities.json")
                val jsonArray = JSONArray(jsonText)
                e.onNext(jsonArray)
                e.onComplete()
            } catch (error: IOException) {
                e.onError(error)
            } catch (error: JSONException) {
                e.onError(error)
            }
    }
}