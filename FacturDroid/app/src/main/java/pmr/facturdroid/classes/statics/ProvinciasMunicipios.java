package pmr.facturdroid.classes.statics;

public class ProvinciasMunicipios {

    /*
     * Atributos
     */
    private final static String[] MUNICIPIOS_SCT = { "Arona", "Agulo", "Alajeró", "Arafo", "Arico", "Arona", "Barlovento",
            "Breña Alta", "Breña Baja", "Buenavista del Norte", "Candelaria", "El Paso", "El Pinar de El Hierro",
            "El Rosario", "El Sauzal", "El Tanque", "Fasnia", "La Frontera", "Fuencaliente de La Palma", "Garachico",
            "Granadilla de Abona", "Guía de Isora", "Güímar", "Hermigua", "Icod de los Vinos", "La Guancha",
            "La Matanza de Acentejo", "La Orotava", "La Victora de Acentejo", "Los Lanos de Aridane", "Los Realejos",
            "Los Silos", "Puerto de la Cruz", "Puntagorda", "Puntallana", "San Andrés y Sauces",
            "San Cristóbal de La Laguna", "San Juan de la Rambla", "San Miguel de Abona", "San Sebastián de La Gomera",
            "Santa Cruz de La Palma", "Santa Cruz de Tenerife", "Santa Úrsula", "Santiago del Teide", "Tacoronte",
            "Tegueste", "Tijarafe", "Valle Gran Rey", "Vallehermoso", "Valverde", "Vilaflor de Chasna",
            "Villa de Mazo" };

    private final static String[] MUNICIPIOS_LP = { "Agaete", "Agüimes", "Antigua", "Arrecife", "Artenara", "Arucas",
            "Betancuria", "Firgas", "Gáldar", "Haría", "Ingenio", "La Aldea de San Nicolás", "La Oliva",
            "Las Palmas de Gran Canaria", "Mogán", "Moya", "Pájara", "Puerto del Rosario", "San Bartolomé",
            "San Bartolomé de Tirajana", "Santa Brígida", "Santa Lucía de Tirajana",
            "Santa María de Guía de Gran Canaria", "Teguise", "Tejeda", "Telde", "Teror", "Tías", "Tinajo", "Tuineje",
            "Valleseco", "Valsequillo de Gran Canaria", "Vega de San Mateo", "Yaiza"};

    private final static String[] PROVINCIAS = {"Santa Cruz de Tenerife", "Las Palmas"};

    /*
     * Getters
     */
    public static String[] getProvincias() {
        return PROVINCIAS;
    }

    public static String[] getMunicipioSCT() {
        return MUNICIPIOS_SCT;
    }

    public static String[] getMunicipioLP() {
        return MUNICIPIOS_LP;
    }

}
