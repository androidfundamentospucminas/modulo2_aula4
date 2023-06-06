package com.walker.modelo2aula4androidfundamentos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.walker.modelo2aula4androidfundamentos.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val listaDeTarefas: ArrayList<String> = ArrayList()
    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listaDeTarefas)

        setupListView()
        setAddButtonClickListener()
        setItemLongClickListener()
    }

    private fun setItemLongClickListener() {
        binding.lista.setOnItemLongClickListener { _, _, position, _ ->
            val alertDialogBuilder = AlertDialog.Builder(this)
            alertDialogBuilder.setTitle("Atenção!")
            alertDialogBuilder.setMessage("Você quer mesmo apagar esse item?")

            alertDialogBuilder.setPositiveButton("Confirmar") { dialog, _ ->
                listaDeTarefas.removeAt(position)
                adapter.notifyDataSetChanged()
                dialog.dismiss()
            }

            alertDialogBuilder.setNegativeButton("Cancelar") { dialog, _ ->
                dialog.dismiss()
            }

            alertDialogBuilder.create().show()

            true
        }
    }

    private fun setupListView() {
        binding.lista.adapter = adapter
    }

    private fun setAddButtonClickListener() {
        binding.button.setOnClickListener {
            val value = binding.editTextText.text.toString()

            // Verificar se value não "" e se não é null
            if (value.isNotBlank()) {
                binding.editTextText.setText("")
                listaDeTarefas.add(value)
                adapter.notifyDataSetChanged()
            } else {
                Toast.makeText(this, "Você não pode adicionar uma tarefa sem título", Toast.LENGTH_SHORT).show()
            }
        }
    }
}