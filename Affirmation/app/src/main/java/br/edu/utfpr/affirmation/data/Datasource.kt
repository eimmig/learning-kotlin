package br.edu.utfpr.affirmation.data

import br.edu.utfpr.affirmation.R
import br.edu.utfpr.affirmation.model.Affirmation

class Datasource {
    fun loadAffirmation() : List<Affirmation> {
        return listOf<Affirmation> (
            Affirmation(R.string.affirmation1),
            Affirmation(R.string.affirmation2),
            Affirmation(R.string.affirmation3),
            Affirmation(R.string.affirmation4),
            Affirmation(R.string.affirmation5),
            Affirmation(R.string.affirmation6),
            Affirmation(R.string.affirmation7),
            Affirmation(R.string.affirmation8),
            Affirmation(R.string.affirmation9),
            Affirmation(R.string.affirmation10),
        )
    }
}