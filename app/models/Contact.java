package models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import play.data.validation.Constraints;
import play.db.ebean.Model;

import com.avaje.ebean.Page;

/**
 * Computer entity managed by Ebean
 */
@Entity 
public class Contact extends Model {

    @Id
    public Long id;
    
    @Constraints.Required
    public String lastName;
    
    @Constraints.Required
    public String firstName;
   
    @Constraints.Pattern(value="[\\d]+")
    public String phoneNumber;
    
    @Constraints.Pattern(value="[\\d]+")
    public String mobileNumber;
    
    @ManyToOne
    public AddressBook addressBook;
    
    /**
     * Generic query helper for entity Computer with id Long
     */
    public static Finder<Long,Contact> find = new Finder<Long,Contact>(Long.class, Contact.class); 
    
    /**
	 * Return a page of computer
	 * 
	 * @param page
	 *            Page to display
	 * @param pageSize
	 *            Number of contacts per page
	 * @param sortBy
	 *            Contact property used for sorting
	 * @param order
	 *            Sort order (either or asc or desc)
	 * @param filter
	 *            Filter applied on the name column
	 */
    public static Page<Contact> page(int page, int pageSize, String sortBy, String order, String filter) {
        return 
            find.where()
                .ilike("lastName", "%" + filter + "%")
                .orderBy(sortBy + " " + order)
                .fetch("addressBook")
                .findPagingList(pageSize)
                .getPage(page);
    }
    
}

