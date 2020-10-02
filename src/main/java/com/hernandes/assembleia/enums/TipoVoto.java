package com.hernandes.assembleia.enums;

public enum TipoVoto {

    NAO(1, "Não"),
    SIM(2, "Sim");

    private int cod;
    private String descricao;

    private TipoVoto(int cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public int getCod() {
        return cod;
    }

    public String getDescricao() {
        return descricao;
    }

    public static TipoVoto toEnum(Integer cod) {
        if (cod == null) {
            return null;
        }

        for (TipoVoto x : TipoVoto.values()) {
            if (cod.equals(x.getCod())) {
                return x;
            }
        }

        throw new IllegalArgumentException("Id inválido: " + cod);

    }

}