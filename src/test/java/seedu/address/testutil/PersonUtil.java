package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NICKNAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TELEGRAM_HANDLE;

import java.util.Set;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Role;

/**
 * A utility class for Person.
 */
public class PersonUtil {

    /**
     * Returns an add command string for adding the {@code person}.
     */
    public static String getAddCommand(Person person) {
        return AddCommand.COMMAND_WORD + " " + getPersonDetails(person);
    }

    /**
     * Returns the part of command string for the given {@code person}'s details.
     */
    public static String getPersonDetails(Person person) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + person.getName().fullName + " ");
        sb.append(PREFIX_TELEGRAM_HANDLE + person.getTelegramHandle().value + " ");
        sb.append(PREFIX_EMAIL + person.getEmail().value + " ");
        sb.append(PREFIX_STUDENT_STATUS + person.getStudentStatus().value + " ");
        person.getRoles().stream().forEach(
            s -> sb.append(PREFIX_ROLE + s.roleName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditPersonDescriptor}'s details.
     */
    public static String getEditPersonDescriptorDetails(EditPersonDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getTelegramHandle().ifPresent(phone -> sb.append(PREFIX_TELEGRAM_HANDLE)
                .append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getStudentStatus()
                .ifPresent(studentStatus -> sb.append(PREFIX_STUDENT_STATUS).append(studentStatus.value).append(" "));
        descriptor.getNickname().ifPresent(nickname -> sb.append(PREFIX_NICKNAME).append(nickname.value).append(" "));
        if (descriptor.getRoles().isPresent()) {
            Set<Role> roles = descriptor.getRoles().get();
            if (roles.isEmpty()) {
                sb.append(PREFIX_ROLE);
            } else {
                roles.forEach(s -> sb.append(PREFIX_ROLE).append(s.roleName).append(" "));
            }
        }
        return sb.toString();
    }
}
