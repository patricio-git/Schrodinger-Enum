package dominio.enumsAMockear;

import dominio.Prenda;

import java.util.List;

public enum Subcategoria {
    NINGUNA(0),
    CABEZA(1),
    MANOS(.5),
    CUELLO(.1);

    private double conversionUnidadCalor;

    Subcategoria(double conversionUnidadCalor) {
        this.conversionUnidadCalor = conversionUnidadCalor;
    }

    public double obtenerTemperatura(List<Prenda> prendas) {
        return obtenerUnidadCalor(prendas) * conversionUnidadCalor;
    }

    private int obtenerUnidadCalor(List<Prenda> prendas) {
        return prendas.stream()
                .map(Prenda::getUnidadCalor)
                .mapToInt(unidadCalor -> unidadCalor).sum();
    }
}
