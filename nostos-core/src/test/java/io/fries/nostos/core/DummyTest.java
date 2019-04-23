package io.fries.nostos.core;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DummyTest {

    @Test
    void should_create_dummy_instance() {
        assertThat(new Dummy()).isNotNull();
    }
}