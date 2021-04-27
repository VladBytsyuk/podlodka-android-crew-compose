package com.vbytsyuk.paccomposeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.vbytsyuk.paccomposeapp.views.SessionCard

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { Content() }
    }
}

@Preview
@Composable
fun Content() = Column {
    Text("Sessions")
    LazyColumn() {
        MockSessions.forEach { 
            item { SessionCard(session = it) }
        }
    }
}
