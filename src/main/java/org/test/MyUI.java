package org.test;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.data.Property;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;
import de.datenhahn.vaadin.componentrenderer.grid.ComponentGrid;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
@Widgetset("org.test.MyAppWidgetset")
public class MyUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();
        final ComponentGrid<Person> grid = new ComponentGrid<>(Person.class);

        grid.setRows(Person.createDummyPersons(100));

        grid.addComponentColumn("name", person -> {
            TextField textField = new TextField();
            textField.setValue(person.getName());
            textField.addValueChangeListener(e -> {
                person.setName((String) e.getProperty().getValue());
            });
            return textField;
        });

        grid.addComponentColumn("favouriteAnimal", person -> {
            ComboBox comboBox = new ComboBox(null, Person.availableAnimals);
            comboBox.setValue(person.getFavouriteAnimal());
            comboBox.addValueChangeListener(e -> {
                person.setFavouriteAnimal((String) e.getProperty().getValue());
            });
            return comboBox;
        });


        layout.addComponents(grid);
        layout.setMargin(true);
        layout.setSpacing(true);
        
        setContent(layout);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
