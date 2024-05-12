package com.example.snap_note

import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.snap_note.Adapters.SnapNoteAdapter
import com.example.snap_note.Model.SnapNoteModel
import com.example.snap_note.Utils.DatabaseHandler
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.Collections

class MainActivity : AppCompatActivity(), DialogCloseListener {
    private var db: DatabaseHandler? = null
    private var tasksRecyclerView: RecyclerView? = null
    private var tasksAdapter: SnapNoteAdapter? = null
    private var fab: FloatingActionButton? = null
    private var taskList: List<SnapNoteModel?>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        db = DatabaseHandler(this)
        db!!.openDatabase()

        tasksRecyclerView = findViewById(R.id.tasksRecyclerView)
        tasksRecyclerView?.layoutManager = LinearLayoutManager(this)

        tasksAdapter = SnapNoteAdapter(db!!, this@MainActivity)
        tasksRecyclerView?.adapter = tasksAdapter

        val itemTouchHelper = ItemTouchHelper(RecyclerItemTouchHelper(tasksAdapter!!))
        itemTouchHelper.attachToRecyclerView(tasksRecyclerView)

        fab = findViewById(R.id.fab)

        taskList = db!!.getAllTasks()
        Collections.reverse(taskList)
        tasksAdapter!!.setTasks(taskList)

        fab?.setOnClickListener {
            AddNewTask.newInstance().show(supportFragmentManager, AddNewTask.TAG)
        }
    }

    override fun handleDialogClose(dialog: DialogInterface?) {
        taskList = db?.getAllTasks()
        Collections.reverse(taskList)
        tasksAdapter?.setTasks(taskList as List<SnapNoteModel>?)
        tasksAdapter?.notifyDataSetChanged()
    }
}
