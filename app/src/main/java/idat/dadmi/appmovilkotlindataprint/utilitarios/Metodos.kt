package idat.dadmi.appmovilkotlindataprint.utilitarios

class Metodos {
    companion object {
        fun obtenerToken(): String? {
            return SharedPreferencesManager()
                .getSomeStringValue(Constantes().PREF_TOKEN)
        }
    }
}