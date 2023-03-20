package com.example.application.Api

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.RequestFuture
import com.android.volley.toolbox.Volley
import com.example.application.MyApplication
import com.example.application.types.Eleve
import org.json.JSONArray
import org.json.JSONObject
import java.util.concurrent.TimeUnit


class ApiCli constructor(context: Context?) {
    private var queue:RequestQueue? = null
    private var contexte:Context? = null

    companion object{
        private var instance: ApiCli? = null
        private val URL = "https://dev-restandroid.users.info.unicaen.fr/REST"

        fun getInstance(contexte: Context): ApiCli{
            if ( instance == null ) {
                instance = ApiCli(contexte)
                }
            return instance!!
        }
    }
    init{
        this.queue = Volley.newRequestQueue(MyApplication.appContext)
        this.contexte = MyApplication.appContext
    }

    private fun recupTout(typeRecherche: String): RequestFuture<JSONArray> {
        val fut = RequestFuture.newFuture<JSONArray>()
        val requete = JsonArrayRequest(Request.Method.GET, "$URL/$typeRecherche/", JSONArray(), fut, fut)
        fut.setRequest(requete)
        requete.tag = this.contexte
        queue?.add(requete)

        return fut
    }


    fun AfficheEleves() : ArrayList<Eleve> {
        val requeteFuture: RequestFuture<JSONArray> = recupTout("student")
        val reponse = requeteFuture.get(2, TimeUnit.MINUTES)
        val listeEleve: ArrayList<Eleve> = ArrayList()

        for (i: Int in 0 until reponse.length()) {
            val obj: JSONObject = reponse.getJSONObject(i)
            val eleve = Eleve(
                obj.getInt("id"),
                obj.getString("name"),
                obj.getInt("formationId"),
                obj.getString("phone"),
                obj.getBoolean("deleted")
            )
            if (eleve.supprime == false) {
                listeEleve.add(eleve)
            }
        }
        return listeEleve
    }
}