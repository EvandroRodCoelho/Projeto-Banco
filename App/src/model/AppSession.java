package model;

public class AppSession {
    private static Usuario usuarioLogado;
    private static Conta contaUsuarioLogado;

    public static Usuario getUsuarioLogado() {
        return usuarioLogado;
    }
    public static void setUsuarioLogado(Usuario usuarioLogado) {
        AppSession.usuarioLogado = usuarioLogado;
    }
    public static Conta getContaUsuarioLogado() {
        return contaUsuarioLogado;
    }
    public static void setContaUsuarioLogado(Conta contaUsuarioLogado) {
        AppSession.contaUsuarioLogado = contaUsuarioLogado;
    }
}
