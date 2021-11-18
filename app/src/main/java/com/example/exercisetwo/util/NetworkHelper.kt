package com.example.exercisetwo.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.os.Build
import androidx.annotation.RequiresApi

object NetworkHelper {
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun isNetworkConnected(context: Context):Boolean{
        var result=false
        (context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).apply {

            if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
                result= cheackNetworkConnection(this,this.activeNetwork)

            }else{
                val networks=this.allNetworks
                for (network in networks){
                    if(cheackNetworkConnection(this,network)){
                        result=true
                    }

                }

            }
        }
      return result
    }
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun cheackNetworkConnection(connectivityManager: ConnectivityManager, network: Network?):Boolean{
connectivityManager.getNetworkCapabilities(network)?.also {
    if(it.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)){
        return true
    }else if(it.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)){
        return true
    }

}
        return false
    }
}