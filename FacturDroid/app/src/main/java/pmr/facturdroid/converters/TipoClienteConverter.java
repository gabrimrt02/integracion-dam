package pmr.facturdroid.converters;

import org.bson.Document;

import pmr.facturdroid.classes.statics.TipoCliente;

public class TipoClienteConverter {

    public static Document convert(TipoCliente tCliente) {
        return new Document("tipo", tCliente.getTipo());
    }

    public static TipoCliente convert(Document documento) {
        TipoCliente returnable = null;
        switch (documento.getString("tipo")) {
            case "PARTICULAR":
                returnable = TipoCliente.PARTICULAR();
                break;
            case "SOCIEDAD LIMITADA":
                returnable = TipoCliente.SOCIEDAD_LIMITADA();
                break;
            case "SOCIEDAD CIVIL":
                returnable = TipoCliente.SOCIEDAD_CIVIL();
                break;
            case "COOPERATIVA":
                returnable = TipoCliente.COOPERATIVA();
                break;
        }

        return returnable;
    }

}
