package com.sinelnikov.quest.deserializer;

import com.sinelnikov.quest.treeNode.TreeNode;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static com.sinelnikov.quest.deserializer.BinaryTreeDeserializer.*;
import static org.junit.jupiter.api.Assertions.*;

class BinaryTreeDeserializerTest {

    BinaryTreeDeserializer deserializer;

    @ParameterizedTest
    @NullAndEmptySource
    void deserialize_passedNullAndEmptyData_shouldThrowException(String data) {
        deserializer = new BinaryTreeDeserializer();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> deserializer.deserialize(data));
        assertEquals("no data for deserializing", exception.getMessage());
    }

    @Test
    void deserialize_passedNullSigh_shouldReturnNull() {
        deserializer = new BinaryTreeDeserializer();
        TreeNode actualNode = deserializer.deserialize(NULL_NODE_SIGN);
        assertNull(actualNode);
    }

    @Test
    void deserialize_passedPassedNoDelimiterData_shouldThrowException() {
        deserializer = new BinaryTreeDeserializer();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> deserializer.deserialize("value"));
        assertEquals("Delimiter " + DELIMITER + " not found. Game tree configuration file is invalid",
                exception.getMessage());
    }

    @Test
    void deserialize_passedValidData_shouldReturnRootNode() {
        deserializer = new BinaryTreeDeserializer();
        TreeNode actualNode = deserializer.deserialize("welcomeRoot" + DELIMITER + "valueRoot" +
                LINE_BREAK + "#" +
                LINE_BREAK + "#");
        assertEquals("welcomeRoot", actualNode.getWelcomeText());
        assertEquals("valueRoot", actualNode.getValue());
    }

    @Test
    void deserialize_passedValidData_shouldAssignYesChildrenNode() {
        deserializer = new BinaryTreeDeserializer();
        TreeNode actualNode = deserializer.deserialize("welcomeRoot" + DELIMITER + "valueRoot" +
                LINE_BREAK + "yesNodeWelcome" + DELIMITER + "yesNodeValue" +
                LINE_BREAK + "#" +
                LINE_BREAK + "#" +
                LINE_BREAK + "#");
        String actualWelcomeText = actualNode.getYesNode().getWelcomeText();
        String actualValueText = actualNode.getYesNode().getValue();
        assertEquals("yesNodeWelcome", actualWelcomeText);
        assertEquals("yesNodeValue", actualValueText);
    }

    @Test
    void deserialize_passedValidData_shouldAssignNoChildrenNode() {
        deserializer = new BinaryTreeDeserializer();
        TreeNode actualNode = deserializer.deserialize("welcomeRoot" + DELIMITER + "valueRoot" +
                LINE_BREAK + "#" +
                LINE_BREAK + "noNodeWelcome" + DELIMITER + "noNodeValue" +
                LINE_BREAK + "#" +
                LINE_BREAK + "#");
        String actualWelcomeText = actualNode.getNoNode().getWelcomeText();
        String actualValueText = actualNode.getNoNode().getValue();
        assertEquals("noNodeWelcome", actualWelcomeText);
        assertEquals("noNodeValue", actualValueText);
    }


}