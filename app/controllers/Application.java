package controllers;

import static play.data.Form.form;
import models.Contact;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.createForm;
import views.html.editForm;
import views.html.list;

public class Application extends Controller {
	
	/**
     * This result directly redirect to application home.
     */
    public static Result GO_HOME = redirect(
        routes.Application.list(0, "name", "asc", "")
    );
    
    /**
     * Handle default path requests, redirect to computers list
     */
    public static Result index() {
        return GO_HOME;
    }
  
    /**
     * Display the paginated list of Contacts.
     *
     * @param page Current page number (starts from 0)
     * @param sortBy Column to be sorted
     * @param order Sort order (either asc or desc)
     * @param filter Filter applied on Contact names
     */
    public static Result list(int page, String sortBy, String order, String filter) {
        return ok(
            list.render(
                Contact.page(page, 10, sortBy, order, filter),
                sortBy, order, filter
            )
        );
    }
    
    /**
     * Display the 'edit form' of a existing Contact.
     *
     * @param id Id of the Contact to edit
     */
    public static Result edit(Long id) {
        Form<Contact> ContactForm = form(Contact.class).fill(
            Contact.find.byId(id)
        );
        return ok(
            editForm.render(id, ContactForm)
        );
    }
    
    /**
     * Handle the 'edit form' submission 
     *
     * @param id Id of the Contact to edit
     */
    public static Result update(Long id) {
        Form<Contact> ContactForm = form(Contact.class).bindFromRequest();
        if(ContactForm.hasErrors()) {
            return badRequest(editForm.render(id, ContactForm));
        }
        ContactForm.get().update(id);
        flash("success", "Contact " + ContactForm.get().lastName + " has been updated");
        return GO_HOME;
    }
    
    /**
     * Display the 'new Contact form'.
     */
    public static Result create() {
        Form<Contact> ContactForm = form(Contact.class);
        return ok(
            createForm.render(ContactForm)
        );
    }
    
    /**
     * Handle the 'new Contact form' submission 
     */
    public static Result save() {
        Form<Contact> ContactForm = form(Contact.class).bindFromRequest();
        if(ContactForm.hasErrors()) {
            return badRequest(createForm.render(ContactForm));
        }
        ContactForm.get().save();
        flash("success", "Contact " + ContactForm.get().lastName + " has been created");
        return GO_HOME;
    }
    
    /**
     * Handle Contact deletion
     */
    public static Result delete(Long id) {
        Contact.find.ref(id).delete();
        flash("success", "Contact has been deleted");
        return GO_HOME;
    }
}
