
package be.luxuryoverdosis.user.schema.v1;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the be.luxuryoverdosis.user.schema.v1 package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: be.luxuryoverdosis.user.schema.v1
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DeleteUserResponse }
     * 
     */
    public DeleteUserResponse createDeleteUserResponse() {
        return new DeleteUserResponse();
    }

    /**
     * Create an instance of {@link ReadAllUsersRequest }
     * 
     */
    public ReadAllUsersRequest createReadAllUsersRequest() {
        return new ReadAllUsersRequest();
    }

    /**
     * Create an instance of {@link User }
     * 
     */
    public User createUser() {
        return new User();
    }

    /**
     * Create an instance of {@link DeleteUserRequest }
     * 
     */
    public DeleteUserRequest createDeleteUserRequest() {
        return new DeleteUserRequest();
    }

    /**
     * Create an instance of {@link ReadAllUsersResponse }
     * 
     */
    public ReadAllUsersResponse createReadAllUsersResponse() {
        return new ReadAllUsersResponse();
    }

    /**
     * Create an instance of {@link Message }
     * 
     */
    public Message createMessage() {
        return new Message();
    }

    /**
     * Create an instance of {@link ReadUserRequest }
     * 
     */
    public ReadUserRequest createReadUserRequest() {
        return new ReadUserRequest();
    }

    /**
     * Create an instance of {@link ReadUserResponse }
     * 
     */
    public ReadUserResponse createReadUserResponse() {
        return new ReadUserResponse();
    }

    /**
     * Create an instance of {@link CreateOrUpdateUserRequest }
     * 
     */
    public CreateOrUpdateUserRequest createCreateOrUpdateUserRequest() {
        return new CreateOrUpdateUserRequest();
    }

    /**
     * Create an instance of {@link CreateOrUpdateUserResponse }
     * 
     */
    public CreateOrUpdateUserResponse createCreateOrUpdateUserResponse() {
        return new CreateOrUpdateUserResponse();
    }

}
