package com.example.waterflow.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.waterflow.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.java_websocket.client.WebSocketClient
import org.java_websocket.handshake.ServerHandshake
import java.net.HttpURLConnection
import java.net.URI
import java.net.URL

class HomeFragment : Fragment() {

//    private lateinit var tvFlowRate: TextView
//    private lateinit var tvVolume: TextView
//    private lateinit var btnRefresh: Button
//    private lateinit var webSocketClient: WebSocketClient

    private lateinit var tvFlowRate: TextView
    private lateinit var tvVolume: TextView
    private lateinit var database: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        tvFlowRate = view.findViewById(R.id.tvFlowRate)
        tvVolume = view.findViewById(R.id.tvVolume)

        // Initialize Firebase
        database = FirebaseDatabase.getInstance().reference.child("waterflow")

        // Listen for changes
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val flowRate = snapshot.child("flowRate").getValue(Float::class.java)
                val volume = snapshot.child("volume").getValue(Float::class.java)

                flowRate?.let {
                    tvFlowRate.text = "Flow rate: $flowRate L/min"
                }
                volume?.let {
                    tvVolume.text = "Volume: $volume L"
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w("WaterFlowFragment", "Failed to read value.", error.toException())
            }
        })

//        tvFlowRate = view.findViewById(R.id.tvFlowRate)
//        tvVolume = view.findViewById(R.id.tvVolume)
//        btnRefresh = view.findViewById(R.id.btnRefresh)

//        btnRefresh.setOnClickListener {
//            fetchData()
//        }

        // Initialize WebSocket
//        initWebSocket()

        return view
    }
}

//    private fun initWebSocket() {
//        val uri = URI("ws://192.168.106.1:81")
//        createWebSocketClient(uri)
//        webSocketClient.connect()
//    }
//
//    private fun createWebSocketClient(uri: URI) {
//        webSocketClient = object : WebSocketClient(uri) {
//            override fun onOpen(handshakedata: ServerHandshake?) {
//                Log.i("WebSocket", "Connected to ${uri.host}")
//            }
//
//            override fun onMessage(message: String?) {
//                Log.i("WebSocket", "Message received: $message")
//                message?.let {
//                    val data = it.split("\n")
//                    if (data.size >= 2) {
//                        val flowRate = data[0].split(": ")[1]
//                        val volume = data[1].split(": ")[1]
//                        activity?.runOnUiThread {
//                            tvFlowRate.text = "Flow rate: $flowRate L/min"
//                            tvVolume.text = "Volume: $volume L"
//                        }
//                    }
//                }
//            }
//
//            override fun onClose(code: Int, reason: String?, remote: Boolean) {
//                Log.i("WebSocket", "Closed with reason: $reason")
//            }
//
//            override fun onError(ex: Exception?) {
//                Log.e("WebSocket", "Error: ${ex?.message}")
//            }
//        }
//    }
//
//    private fun fetchData() {
//        // Optional: Add logic to request data refresh from the server if needed
//        webSocketClient.send("Requesting data refresh")
//    }
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//        webSocketClient.close()
//    }
//}