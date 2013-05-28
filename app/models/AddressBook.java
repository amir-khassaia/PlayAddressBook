package models;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.data.validation.Constraints;
import play.db.ebean.Model;


/**
 * Address Book entity managed by Ebean
 */
@Entity 
public class AddressBook extends Model {

    @Id
    public Long id;
    
    @Constraints.Required
    public String name;
    
    /**
	 * Generic query helper for entity AddressBook with id Long
	 */
    public static Model.Finder<Long,AddressBook> find = new Model.Finder<Long,AddressBook>(Long.class, AddressBook.class);

    public static Map<String,String> options() {
        LinkedHashMap<String,String> options = new LinkedHashMap<String,String>();
        for(AddressBook b: AddressBook.find.orderBy("name").findList()) {
            options.put(b.id.toString(), b.name);
        }
        return options;
    }

}

