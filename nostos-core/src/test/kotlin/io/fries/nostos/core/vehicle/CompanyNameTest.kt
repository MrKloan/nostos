package io.fries.nostos.core.vehicle

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.api.Test

internal class CompanyNameTest {

    @Test
    internal fun should_throw_when_the_company_name_is_empty() {
        assertThatIllegalArgumentException()
                .isThrownBy { CompanyName("") }
                .withMessage("Company name cannot be empty")
                .withNoCause()
    }

    @Test
    internal fun should_trim_company_name() {
        val companyName = CompanyName("  Company     ")
        assertThat(companyName.name).isEqualTo("Company")
    }
}