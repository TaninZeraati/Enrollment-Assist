package ir.proprog.enrollassist.controller.course;

import com.fasterxml.jackson.databind.ObjectMapper;
import ir.proprog.enrollassist.DataInitializer;
import ir.proprog.enrollassist.EnrollAssistApplication;
import ir.proprog.enrollassist.controller.course.CourseController;
import ir.proprog.enrollassist.controller.course.CourseMajorView;
import ir.proprog.enrollassist.controller.course.CourseView;
import ir.proprog.enrollassist.domain.course.AddCourseService;
import ir.proprog.enrollassist.domain.course.Course;
import ir.proprog.enrollassist.repository.CourseRepository;
import net.minidev.json.parser.JSONParser;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = EnrollAssistApplication.class)
@AutoConfigureMockMvc
public class CourseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    CourseRepository courseList;


    @Autowired
    private ObjectMapper objectMapper;

    @Before
    void addCourseToList() throws Exception {

        Course test = new Course("7777777", "SoftwareTesting", 3, "Undergraduate");
        Course narm2 = new Course("1111111", "Software", 3, "Undergraduate");
        Course compiler = new Course("2222222", "compiler", 3, "Undergraduate");
        Course omomi = new Course("3333333", "Omomi", 2, "Undergraduate");
        courseList.saveAll(List.of(test, narm2, compiler, omomi));

    }

    @Test
    void getAllCourseReturns200() throws Exception {
        mockMvc.perform(get("/courses")
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    void Returns404GetCourseTest() throws Exception {
        mockMvc.perform(get("/courses/121212121")
                        .contentType("application/json"))
                .andExpect(status().isNotFound());
    }


    @Test
    void addCourseTest() throws Exception {
        Course IE = new Course("5555555", "IE", 3, "Undergraduate");

        var course = new CourseMajorView(IE, new HashSet<Long>(), new HashSet<Long>());
        mockMvc.perform(post("/courses")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(course)))
                .andExpect(status().isOk());
    }

    @Test
    void BadRequestTest() throws Exception {
        mockMvc.perform(post("/courses")
                        .contentType("application/json"))
                .andExpect(status().isBadRequest());
    }
}