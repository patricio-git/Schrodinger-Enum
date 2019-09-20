package dominio.enumsAMockear;

import dominio.Material;
import dominio.Prenda;
import dominio.TipoCapa;

import java.util.ArrayList;
import java.util.List;

public enum Categoria {
    ACCESORIO(new ArrayList<Material>() {{
        add(Material.PLASTICO);
        add(Material.CUERO);
    }},
        new ArrayList<TipoCapa>() {{
        add(TipoCapa.MINIMA);
        add(TipoCapa.SUBINTERMEDIA);
        add(TipoCapa.INTERMEDIA);
        add(TipoCapa.MAXIMA);
    }},
        .3) {
        @Override
        public boolean esSubcategoriaValida(Subcategoria subcategoria) {
            return new ArrayList<Subcategoria>() {{
                add(Subcategoria.CABEZA);
                add(Subcategoria.CUELLO);
                add(Subcategoria.MANOS);
            }}.contains(subcategoria);
        }
    },
    CALZADO(new ArrayList<Material>() {{
        add(Material.CUERO);
        add(Material.PLASTICO);
    }},
        new ArrayList<TipoCapa>() {{
        add(TipoCapa.MINIMA);
    }},
            1),
    INFERIOR(new ArrayList<Material>() {{
        add(Material.LANA);
        add(Material.CUERO);
        add(Material.PLASTICO);
        add(Material.ALGODON);
    }},
        new ArrayList<TipoCapa>() {{
            add(TipoCapa.MINIMA);
        }},
            1),
    SUPERIOR(new ArrayList<Material>() {{
        add(Material.LANA);
        add(Material.CUERO);
        add(Material.PLASTICO);
        add(Material.ALGODON);
    }},
        new ArrayList<TipoCapa>() {{
        add(TipoCapa.MINIMA);
        add(TipoCapa.SUBINTERMEDIA);
        add(TipoCapa.INTERMEDIA);
        add(TipoCapa.MAXIMA);
    }},
            .3);

    private List<Material> materialesValidos;
    private List<TipoCapa> capasValidas;
    private double conversionUnidadCalor;

    Categoria(List<Material> materialesValidos, List<TipoCapa> capasValidas, double conversionUnidadCalor) {
        this.materialesValidos = materialesValidos;
        this.capasValidas = capasValidas;
        this.conversionUnidadCalor = conversionUnidadCalor;
    }

    public boolean esMaterialValido(Material material) {
        return this.materialesValidos.contains(material);
    }

    public boolean esTipoCapaValido(TipoCapa capa) { return this.capasValidas.contains(capa); }

    public double obtenerTemperatura(List<Prenda> prendas) {
        return obtenerUnidadCalor(prendas) * conversionUnidadCalor;
    }

    public boolean esSubcategoriaValida(Subcategoria subcategoria) {
        return subcategoria == Subcategoria.NINGUNA;
    }

    private int obtenerUnidadCalor(List<Prenda> prendas) {
        return prendas.stream()
                .map(Prenda::getUnidadCalor)
                .mapToInt(unidadCalor -> unidadCalor).sum();
    }
}
