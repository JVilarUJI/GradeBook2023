package es.uji.jvilar.gradebook

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import es.uji.jvilar.gradebook.database.Subject
import es.uji.jvilar.gradebook.dialogs.SubjectDialog
import es.uji.jvilar.gradebook.dialogs.SubjectDialog.SubjectListener

class MainActivity : AppCompatActivity(), GradeView, SubjectListener {

    private lateinit var subjectView: RecyclerView
    private lateinit var noSubjectText: TextView
    private lateinit var fab: FloatingActionButton
    private lateinit var presenter: Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        fab = findViewById(R.id.fab)
        fab.setOnClickListener {
            Toast.makeText(this, "Add new grade", Toast.LENGTH_LONG).show()
        }

        subjectView = findViewById(R.id.subjectView)
        noSubjectText = findViewById(R.id.noSubjectText)
        subjectView.layoutManager = LinearLayoutManager(this)
        presenter = Presenter(Model(applicationContext), this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_add_subject -> {
                presenter.onNewSubjectRequested()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun askForSubject() = SubjectDialog().show(supportFragmentManager, "Subject")

    override fun showSubjects(subjects: List<Subject>) {
        if (subjects.isEmpty()) {
            noSubjectText.visibility = View.VISIBLE
            subjectView.visibility = View.GONE
        } else {
            noSubjectText.visibility = View.GONE
            subjectView.visibility = View.VISIBLE
            subjectView.adapter =
                SubjectAdapter(subjects)
        }
    }

    override fun onSubjectAvailable(subject: Subject) = presenter.onNewSubjectAvailable(subject)
}