package study.querydsl.repository.jdbctemplate;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import study.querydsl.domain.Album;
import study.querydsl.domain.Book;
import study.querydsl.domain.Item;
import study.querydsl.domain.Movie;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class JdbcTemplateItemRepository {

    private final JdbcTemplate jdbcTemplate;

    public AlbumRowMapper albumRowMapper = new AlbumRowMapper();
    public BookRowMapper bookRowMapper = new BookRowMapper();
    public MovieRowMapper movieRowMapper = new MovieRowMapper();

    public Optional<Item> findById(Long itemId) {
        String sql = "SELECT * FROM item WHERE item_id = ?";
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
                String dtype = rs.getString("dtype");
                if ("A".equals(dtype)) {
                    return albumRowMapper.mapRow(rs, rowNum);
                } else if ("B".equals(dtype)) {
                    return bookRowMapper.mapRow(rs, rowNum);
                } else if ("M".equals(dtype)) {
                    return movieRowMapper.mapRow(rs, rowNum);
                }
                return null;
            }, itemId));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public void save(Item item) {
        String sql = "INSERT INTO item (dtype, name, price, stock_quantity, item_detail) VALUES (?, ?, ?, ?, ?)";
        log.info("findById called with itemId: {}", item.getId());

        jdbcTemplate.update(sql, item.getDtype(), item.getName(), item.getPrice(), item.getStockQuantity(), item.getItemDetail());
    }

    public void delete(Long itemId) {
        String sql = "DELETE FROM item WHERE item_id = ?";
        log.info("findById called with itemId: {}", itemId);
        jdbcTemplate.update(sql, itemId);
    }

    public void update(Item item) {
        String sql = "UPDATE item SET name = ?, price = ?, stock_quantity = ?, item_detail = ? WHERE item_id = ?";
        log.info("findById called with itemId: {}", item.getId());
        jdbcTemplate.update(sql, item.getName(), item.getPrice(), item.getStockQuantity(), item.getItemDetail(), item.getId());
    }

    public void updateItemDetail(Long itemId, String itemDetail) {
        String sql = "UPDATE item SET item_detail = ? WHERE item_id = ?";
        jdbcTemplate.update(sql, itemDetail, itemId);
    }

    public void addItemDetail(Long itemId, String itemDetail) {
        String sql = "UPDATE item SET item_detail = ? WHERE item_id = ?";
        jdbcTemplate.update(sql, itemDetail, itemId);
    }

    public void deleteItemDetail(Long itemId) {
        String sql = "UPDATE item SET item_detail = null WHERE item_id = ?";
        jdbcTemplate.update(sql, itemId);
    }

    public static class BookRowMapper implements RowMapper<Book> {
        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            Book book = new Book();
            book.setId(rs.getLong("item_id"));
            book.setName(rs.getString("name"));
            book.setPrice(rs.getInt("price"));
            book.setStockQuantity(rs.getInt("stock_quantity"));
            book.setItemDetail(rs.getString("item_detail"));
            book.setAuthor(rs.getString("author"));
            book.setIsbn(rs.getString("isbn"));
            return book;
        }
    }

    public static class MovieRowMapper implements RowMapper<Movie> {
        @Override
        public Movie mapRow(ResultSet rs, int rowNum) throws SQLException {
            Movie movie = new Movie();
            movie.setId(rs.getLong("item_id"));
            movie.setName(rs.getString("name"));
            movie.setPrice(rs.getInt("price"));
            movie.setStockQuantity(rs.getInt("stock_quantity"));
            movie.setItemDetail(rs.getString("item_detail"));
            movie.setDirector(rs.getString("director"));
            movie.setActor(rs.getString("actor"));
            return movie;
        }
    }

    public static class AlbumRowMapper implements RowMapper<Album> {
        @Override
        public Album mapRow(ResultSet rs, int rowNum) throws SQLException {
            Album album = new Album();
            album.setId(rs.getLong("item_id"));
            album.setName(rs.getString("name"));
            album.setPrice(rs.getInt("price"));
            album.setStockQuantity(rs.getInt("stock_quantity"));
            album.setItemDetail(rs.getString("item_detail"));
            album.setArtist(rs.getString("artist"));
            album.setEtc(rs.getString("etc"));
            return album;
        }
    }
}

