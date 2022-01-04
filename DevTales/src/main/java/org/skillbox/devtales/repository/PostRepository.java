package org.skillbox.devtales.repository;

import org.skillbox.devtales.dto.PostDto;
import org.skillbox.devtales.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

    String HEAD_QUERY = "select p " +
            "from Post p " +
            "where p.isActive = 1 and p.moderationStatus = 'ACCEPTED' " +
            "and p.dateTime <= current_time ";

    @Query(HEAD_QUERY)
    Page<Post> findAllActiveAndAcceptedPosts(Pageable pageable);

    @Query(HEAD_QUERY +
            "order by p.dateTime desc ")
    Page<Post> findRecentPostsSortedByDate(Pageable pageable);

    @Query(HEAD_QUERY +
            "order by p.dateTime asc ")
    Page<Post> findNewPostsSortedByDate(Pageable pageable);

    @Query("select p from Post p " +
            "where p.isActive = 1 and p.moderationStatus = 'ACCEPTED' " +
            "and p.dateTime <= current_time " +
            "group by p " +
            "order by p.postVotes.size desc ")
    Page<Post> findBestPostsSortedByLikeCount(Pageable pageable);

    @Query(value = "select p.id, p.date_time, p.is_active, p.moderation_status, p.text, p.title, p.view_count, p.moderator_id, p.user_id, " +
            "SUM(CASE " +
            "WHEN pv.value = 1 THEN 1 ELSE 0 END) " +
            "as like_count, " +
            "SUM(CASE " +
            "WHEN pv.value = -1 THEN 1 ELSE 0 END) " +
            "as dislike_count " +
            "from posts p " +
            "left join post_votes pv on p.id = pv.post_id " +
            "where p.is_active = 1 and p.moderation_status = 'ACCEPTED' and p.date_time <= current_time() " +
            "group by p.id " +
            "order by like_count desc ", nativeQuery = true)
    Page<PostDto> findBestPostsByLikeCount(Pageable pageable);

    @Query(HEAD_QUERY +
            "group by p order by p.comments.size desc")
    Page<Post> findPopularPostsSortedByCommentsCount(Pageable pageable);

    @Query("select p from Post p " +
            "where p.isActive = 1 and p.moderationStatus = 'ACCEPTED' " +
            "and p.dateTime <= current_time " +
            "and (p.title like '%'||:text||'%' or p.text like '%'||:text||'%') " +
            "group by p " +
            "order by p.dateTime desc ")
    Page<Post> findPostsByText(String text, Pageable pageable);

    @Query(value = "select * " +
            "from posts p " +
            "join tag2post tp on p.id = tp.post_id " +
            "join tags t on tp.tag_id = t.id " +
            "where p.is_active = 1 and p.moderation_status = 'ACCEPTED' and p.date_time <= current_time " +
            "and t.name = :tag " +
            "group by p.id ", nativeQuery = true)
    Page<Post> findPostsByTags(String tag, Pageable pageable);

    @Query(value = "select * " +
            "from posts p " +
            "where p.is_active = 1 and p.moderation_status = 'ACCEPTED' " +
            "and p.date_time <= current_time " +
            "and date_format(p.date_time, '%Y-%m-%d') = :date ", nativeQuery = true)
    Page<Post> findPostsByDate(String date, Pageable pageable);

    @Query(value = "select date_format(p.date_time, '%Y') as p_year " +
            "from posts p " +
            "where p.is_active = 1 and p.moderation_status = 'ACCEPTED' and p.date_time <= current_time " +
            "group by p_year " +
            "order by p_year ", nativeQuery = true)
    Integer[] findYearsOfPosts();

    @Query(value = "select date_format(p.date_time, '%Y-%m-%d') as p_date, count(p.id) as p_count " +
            "from posts p " +
            "where p.is_active = 1 and p.moderation_status = 'ACCEPTED' and p.date_time <= current_time " +
            "and date_format(p.date_time, '%Y') = :year " +
            "group by p_date ", nativeQuery = true)
    List<String> findPostsByYear(int year);

}
