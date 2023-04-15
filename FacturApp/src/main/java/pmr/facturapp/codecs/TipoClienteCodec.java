package pmr.facturapp.codecs;

import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;

import pmr.facturapp.classes.statics.TipoCliente;

public class TipoClienteCodec implements Codec<TipoCliente>{

    @Override
    public void encode(BsonWriter writer, TipoCliente value, EncoderContext encoderContext) {
        writer.writeStartDocument();
        writer.writeString("tipo", value.getTipo());
        writer.writeEndDocument();
    }

    @Override
    public Class<TipoCliente> getEncoderClass() {
        return TipoCliente.class;
    }

    @Override
    public TipoCliente decode(BsonReader reader, DecoderContext decoderContext) {
        TipoCliente returnable = null;
        reader.readStartDocument();
        String tipo = reader.readString("tipo");
        reader.readEndDocument();
        switch (tipo) {
            case "PARTICULAR":
                returnable = TipoCliente.PARTICULAR();
            case "SOCIEDAD LIMITADA":
                returnable = TipoCliente.SOCIEDAD_LIMITADA();
            case "SOCIEDAD CIVIL":
                returnable = TipoCliente.SOCIEDAD_CIVIL();
            case "COOPERATIVA":
                returnable = TipoCliente.COOPERATIVA();
        }
        return returnable;
    }
}
