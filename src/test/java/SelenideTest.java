import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class SelenideTest {

    @BeforeEach
    public void openForm() {
        open("http://localhost:9999");
    }

    @Test
    void testSelenideSuccess() {
        $("[data-test-id=name] input").sendKeys("Иван Петров");
        $("[data-test-id=phone] input").sendKeys("+79012345678");
        $("[data-test-id=agreement]").click();
        $("[type=button").click();
        $("[data-test-id=order-success]").shouldHave(Condition.text(" Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void testSelenideNameLat() {
        $("[data-test-id=name] input").sendKeys("Ivan Petrov");
        $("[data-test-id=phone] input").sendKeys("+79032348679");
        $("[data-test-id=agreement]").click();
        $("[type=button").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(Condition.text("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void testSelenidPhone() {
        $("[data-test-id=name] input").sendKeys("Иван-Ваня Петров");
        $("[data-test-id=phone] input").sendKeys("879032348679");
        $("[data-test-id=agreement]").click();
        $("[type=button").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(Condition.text("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void testSelenidClick() {
        $("[data-test-id=name] input").sendKeys("Иван-Ваня Петров");
        $("[data-test-id=phone] input").sendKeys("+79032348679");
        $("[type=button").click();
        $("[data-test-id=agreement].input_invalid .checkbox__text").shouldHave(Condition.text("Я соглашаюсь с условиями обработки и использования моих персональных данных и разрешаю сделать запрос в бюро кредитных историй"));
    }

    @Test
    void testSelenideNameEmpty() {
        $("[data-test-id=name] input").sendKeys("");
        $("[data-test-id=phone] input").sendKeys("+79032348679");
        $("[data-test-id=agreement]").click();
        $("[type=button").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(Condition.text("Поле обязательно для заполнения"));
    }

    @Test
    void testSelenidPhoneEmpty() {
        $("[data-test-id=name] input").sendKeys("Иван-Ваня Петров");
        $("[data-test-id=phone] input").sendKeys("");
        $("[data-test-id=agreement]").click();
        $("[type=button").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(Condition.text("Поле обязательно для заполнения"));
    }
}
