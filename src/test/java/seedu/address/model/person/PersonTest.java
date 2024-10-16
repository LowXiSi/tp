package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROLE_PRESIDENT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STUDENT_STATUS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TELEGRAM_HANDLE_BOB;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BOB;
import static seedu.address.testutil.TypicalPersons.BOBNICK;
import static seedu.address.testutil.TypicalPersons.BOB_HASSAMEEMAIL_ALICE;
import static seedu.address.testutil.TypicalPersons.BOB_HASSAMENICK_BOBNICK;
import static seedu.address.testutil.TypicalPersons.BOB_HASSAMETELE_ALICE;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class PersonTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Person person = new PersonBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> person.getRoles().remove(0));
    }

    @Test
    public void isSamePerson() {
        // same object -> returns true
        assertTrue(ALICE.isSamePerson(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSamePerson(null));

        // same name, all other attributes different -> returns true
        Person editedAlice = new PersonBuilder(ALICE).withTelegramHandle(VALID_TELEGRAM_HANDLE_BOB)
                .withEmail(VALID_EMAIL_BOB)
                .withStudentStatus(VALID_STUDENT_STATUS_BOB).withRoles(VALID_ROLE_PRESIDENT).build();
        assertTrue(ALICE.isSamePerson(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new PersonBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.isSamePerson(editedAlice));

        // name differs in case, all other attributes same -> returns false
        Person editedBob = new PersonBuilder(BOB).withName(VALID_NAME_BOB.toLowerCase()).build();
        assertFalse(BOB.isSamePerson(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new PersonBuilder(BOB).withName(nameWithTrailingSpaces).build();
        assertFalse(BOB.isSamePerson(editedBob));
    }


    @Test
    public void hasSameFields() {
        // Summary of the below tests, testing Persons where all the traits are different but
        // - same telegram handle
        // - same email
        // - same (nick + name)

        // same object -> returns true
        assertTrue(ALICE.hasSameFields(ALICE));

        // null -> returns false
        assertFalse(ALICE.hasSameFields(null));

        // different fields for (name + nickname), tele handle, email -> false
        assertFalse(ALICE.hasSameFields(BOB));

        // Both of the following Bob's have exactly one conflicting field with Alice
        // same tele -> true ; same email -> true
        assertTrue(BOB_HASSAMETELE_ALICE.hasSameFields(ALICE));
        assertTrue(BOB_HASSAMEEMAIL_ALICE.hasSameFields(ALICE));

        // one is BOB and one is BOBNICK
        // same name + different nick -> false
        assertFalse(BOB.hasSameFields(BOBNICK));

        // same (name + nick) -> true // the bob's below have different email and tele
        assertTrue(BOBNICK.hasSameFields(BOB_HASSAMENICK_BOBNICK));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Person aliceCopy = new PersonBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different person -> returns false
        assertFalse(ALICE.equals(BOB));

        // different name -> returns false
        Person editedAlice = new PersonBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different telegram -> returns false
        editedAlice = new PersonBuilder(ALICE).withTelegramHandle(VALID_TELEGRAM_HANDLE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different email -> returns false
        editedAlice = new PersonBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different student status -> returns false
        editedAlice = new PersonBuilder(ALICE).withStudentStatus(VALID_STUDENT_STATUS_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new PersonBuilder(ALICE).withRoles(VALID_ROLE_PRESIDENT).build();
        assertFalse(ALICE.equals(editedAlice));
    }

    @Test
    public void toStringMethod() {
        String expected = Person.class.getCanonicalName() + "{name=" + ALICE.getName() + ", telegram handle="
                + ALICE.getTelegramHandle() + ", email=" + ALICE.getEmail()
                + ", studentStatus=" + ALICE.getStudentStatus()
                + ", roles=" + ALICE.getRoles() + ", nickname=" + ALICE.getNickname() + "}";
        assertEquals(expected, ALICE.toString());
    }
}
