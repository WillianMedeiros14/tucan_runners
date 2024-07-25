package com.example.runners.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.runners.R
import com.example.runners.databinding.FragmentSplashBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SplashFragment : Fragment() {

    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!

    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val flushOrangeColor = ContextCompat.getColor(requireContext(), R.color.flush_orange_400)
        activity?.window?.statusBarColor = flushOrangeColor


        Handler(Looper.getMainLooper()).postDelayed(this::checkAuth, 3000)

    }
    private fun checkAuth(){
        auth = Firebase.auth


        if(auth.currentUser == null){
            findNavController().navigate(R.id.action_splashFragment_to_authentication)
        }else{
//            findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
//            val navHostFragment = activity?.supportFragmentManager?.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
//            val navController = navHostFragment.navController
//            navController.setGraph(R.navigation.mobile_navigation)

            val intent = Intent(activity, AppAuthenticationActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}