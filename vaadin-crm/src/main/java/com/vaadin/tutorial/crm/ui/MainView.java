package com.vaadin.tutorial.crm.ui;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import com.vaadin.tutorial.crm.backend.entity.Company;
import com.vaadin.tutorial.crm.backend.entity.Contact;
import com.vaadin.tutorial.crm.backend.service.ContactService;

@Route("")
@CssImport("./styles/shared-styles.css")
@CssImport(value = "./styles/vaadin-text-field-styles.css", themeFor = "vaadin-text-field")
public class MainView extends VerticalLayout {

	private static final long serialVersionUID = 1L;

	private ContactService contactService;
	private Grid<Contact> grid = new Grid<>(Contact.class);
	private TextField filterText = new TextField();

	public MainView(ContactService contactService) {
		this.contactService = contactService;
		addClassName("list-view");
		setSizeFull();
		configureFilter();
		configureGrid();
		add(filterText, grid);
		updateList();
	}

	private void configureGrid() {
		grid.addClassName("contact-grid");
		grid.setSizeFull();
		grid.removeColumnByKey("company");
		grid.setColumns("firstName", "lastName", "email", "status");
		grid.addColumn(contact -> {
			Company company = contact.getCompany();
			return company == null ? "-" : company.getName();
		}).setHeader("Company");
	}

	private void updateList() {
		grid.setItems(contactService.findAll(filterText.getValue()));
		grid.getColumns().forEach(col -> col.setAutoWidth(true));
	}

	private void configureFilter() {
		filterText.setPlaceholder("Filter by name...");
		filterText.setClearButtonVisible(true);
		filterText.setValueChangeMode(ValueChangeMode.LAZY);
		filterText.addValueChangeListener(e -> updateList());
	}
}