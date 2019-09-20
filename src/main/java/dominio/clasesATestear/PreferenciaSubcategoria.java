package dominio.clasesATestear;

import dominio.Prenda;
import dominio.enumsAMockear.Categoria;
import dominio.enumsAMockear.Subcategoria;

import java.util.ArrayList;
import java.util.List;

public class PreferenciaSubcategoria extends PreferenciaCategoria {
    private Subcategoria subcategoria;

    public PreferenciaSubcategoria(int temperaturaInferior, int temperaturaSuperior, Categoria categoria, Subcategoria subcategoria) {
        super(temperaturaInferior, temperaturaSuperior, categoria);
        this.subcategoria = subcategoria;
    }

    @Override
    protected double temperatura(List<Prenda> accesorios, int temperaturaEvento) {
        return accesorios.stream()
                .filter(accesorio -> accesorio.poseeSubcategoria(subcategoria))
                .findFirst()
                .map(this::temperaturaPrenda)
                .orElse(0.0);
    }

    private double temperaturaPrenda(Prenda prenda) {
        return this.subcategoria.obtenerTemperatura(new ArrayList<Prenda>() {{add(prenda);}});
    }
}

