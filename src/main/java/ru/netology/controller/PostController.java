package ru.netology.controller;

import com.google.gson.Gson;
import ru.netology.model.Post;
import ru.netology.service.PostService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class PostController {
  public static final String APPLICATION_JSON = "application/json";
  private final PostService service;

  public PostController(PostService service) {
    this.service = service;
  }

  public void all(HttpServletResponse response) throws IOException {
    final Gson gson = initialMethod(response);
    final List<Post> data = service.all();
    writeResponse(data, response, gson);
  }

  public void getById(long id, HttpServletResponse response) throws IOException {
    final Gson gson = initialMethod(response);
    final Post data = service.getById(id);
    writeResponse(data, response, gson);
  }

  public void save(Reader body, HttpServletResponse response) throws IOException {
    final Gson gson = initialMethod(response);
    final Post post = gson.fromJson(body, Post.class);
    final Post data = service.save(post);
    writeResponse(data, response, gson);
  }

  public void removeById(long id, HttpServletResponse response) throws IOException {
    final Gson gson = initialMethod(response);
    service.removeById(id);
    writeResponse(response, gson);
  }

  private static void writeResponse(Post data, HttpServletResponse response, Gson gson) throws IOException {
    response.getWriter().print(gson.toJson(data));
  }

  private static void writeResponse(List<Post> data, HttpServletResponse response, Gson gson) throws IOException {
    response.getWriter().print(gson.toJson(data));
  }

  private static void writeResponse(HttpServletResponse response, Gson gson) throws IOException {
    response.getWriter().print(gson.toJson(HttpServletResponse.SC_OK));
  }

  private static Gson initialMethod(HttpServletResponse response) {
    response.setContentType(APPLICATION_JSON);
    return new Gson();
  }
}

