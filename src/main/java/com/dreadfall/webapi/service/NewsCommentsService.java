package com.dreadfall.webapi.service;

import com.dreadfall.webapi.model.NewsComment;
import com.dreadfall.webapi.model.dto.NewsCommentDto;
import com.dreadfall.webapi.repository.NewsCommentsRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class NewsCommentsService {

    private final NewsCommentsRepository newsCommentsRepository;

    public NewsCommentsService(NewsCommentsRepository newsCommentsRepository) {
        this.newsCommentsRepository = newsCommentsRepository;
    }

    public List<NewsCommentDto> findAllByNewsId(Long newsId) {
        List<Integer> removalList = new ArrayList<>();
        List<NewsCommentDto> finalCommentsList = new ArrayList<>();
        List<NewsCommentDto> unalteredDtoList = newsCommentsRepository.findAllByNewsId(newsId)
                .stream()
                .map(this::mapEntityToDto)
                .toList();
        int listSize = unalteredDtoList.size();

        for (int i = 0, j = 1; i < listSize; i++) {
            NewsCommentDto currentDto = unalteredDtoList.get(i);
            for (int k = j; k < listSize; k++) {
                NewsCommentDto traversedDto = unalteredDtoList.get(k);
                Long currentCommentId = currentDto.getCommentId();
                Long traversedParentId = traversedDto.getParentId();
                if (traversedParentId != null && Objects.equals(currentCommentId, traversedParentId)) {
                    currentDto.getComments().add(traversedDto);
                    removalList.add(k);
                }
            }
            finalCommentsList.add(currentDto);
            j++;
        }

        removalList.sort(Comparator.reverseOrder());
        removalList.forEach(e -> finalCommentsList.remove(e.intValue()));

        return finalCommentsList;
    }

    public NewsCommentDto addComment(Long newsId, NewsCommentDto newsCommentDto) {
        NewsComment newsComment = NewsComment.builder()
                .newsId(newsId)
                .content(newsCommentDto.getContent())
                .build();
        return mapEntityToDto(newsCommentsRepository.save(newsComment));
    }

    @Transactional
    public List<NewsCommentDto> deleteByNewsIdAndCommentIds(Long newsId, Long commentId) {
        List<Long> idDeletionList = new CopyOnWriteArrayList<>(List.of(commentId));
        newsCommentsRepository.findAllByNewsId(newsId)
                .stream()
                .sorted(Comparator.comparing(NewsComment::getCommentId))
                .toList()
                .forEach(newsComment ->
                        idDeletionList.forEach(idToDelete -> {
                            if (Objects.equals(newsComment.getParentId(), idToDelete)) {
                                idDeletionList.add(newsComment.getCommentId());
                            }
                        }));
        newsCommentsRepository.deleteByNewsIdAndCommentIds(newsId, idDeletionList);
        return findAllByNewsId(newsId);
    }

    private NewsCommentDto mapEntityToDto(NewsComment newsComment) {
        return NewsCommentDto.builder()
                .commentId(newsComment.getCommentId())
                .newsId(newsComment.getNewsId())
                .parentId(newsComment.getParentId())
                .content(newsComment.getContent())
                .createdBy(newsComment.getCreatedBy())
                .dateCreated(newsComment.getDateCreated())
                .build();
    }
}