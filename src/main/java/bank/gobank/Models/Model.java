package bank.gobank.Models;

import bank.gobank.Views.ViewFactory;

public class Model {
    private static Model model;
    private final ViewFactory viewFactory;

    // singleton
    private Model() {
        this.viewFactory = new ViewFactory();
    }

    public static synchronized Model getInstance() {
        if (model == null) {
            model = new Model();
        }

        return model;
    }

    public ViewFactory getViewFactory() {
        return viewFactory;
    }
}
