package com.example.matuleme2.domain

import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.Auth
import io.github.jan.supabase.postgrest.Postgrest

object Constants {

    val supabase = createSupabaseClient(
        supabaseUrl = "https://wyipcnltaypsrvioexsi.supabase.co",
        supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Ind5aXBjbmx0YXlwc3J2aW9leHNpIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MzM0NjM5NTEsImV4cCI6MjA0OTAzOTk1MX0.4-4m_L0tK0lGJtby5N0D-Ze98XHAfTXU-Hg1OtJEWg4"
    ) {
        install(Auth)
        install(Postgrest)
    }
}