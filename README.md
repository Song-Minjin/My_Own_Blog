# 나만의 블로그
> Spring Boot로 CRUD 구현하기 과제

<br>

## 🧾 과제 요구사항 리스트

![](https://velog.velcdn.com/images/sw_smj/post/24ae0f2c-9d9b-4816-a676-c42fd7d2d591/image.png){: width="50%" height="50%"}

| 필요 기능 | 기능 설명 |
| --- | --- |
| 전체 게시글 목록 조회 | 제목, 작성자명, 작성 날짜 (내림차순 정렬) |
| 한 게시글 조회 | 제목, 작성자명, 작성 날짜, 작성 내용 |
| 게시글 작성 | 제목, 작성자명, 비밀번호, 작성 내용 |
| 게시글 수정 | 제목, 작성자명, 비밀번호, 작성 내용 |
| 게시글 삭제 | 목록에 조회되지 않도록 처리 |
| 비밀번호 확인 | 해당 게시글의 비밀번호와 일치 여부 판단 |

<br><br>

## 🗄 DB 테이블 설계
![image](https://user-images.githubusercontent.com/100582309/184881172-63747f68-fb1f-49c4-9e12-4c5e71894979.png)


### table name : Post
| Attribute | Data Type | Description | PK | N/N | Check | default |
| --- | --- | --- | --- | --- | --- | --- |
| post_id | INT | 게시글 ID | O | O |  |  |
| post_title | VARCHAR | 게시글 제목 |  | O |  |  |
| post_author | VARCHAR | 게시글 작성자 |  | O |  |  |
| post_datetime | DATE | 게시 날짜 |  | O |  |  |
| post_content | VARCHAR | 게시글 내용 |  | O |  |  |
| post_password | VARCHAR | 게시글 비밀번호 |  | O |  |  |

<br><br>

## 📝 API Document
> 각 Function 이름을 누르면 자세한 HTTP Request, Response를 확인할 수 있습니다.

| Function | Method | URL |<br>
| -- | -- | -- |<br>
|[전체 게시글 목록 조회]()|`GET`|`api/posts`|<br>
|[게시글 작성]()|`POST`|`api/posts`|<br>
|[한 게시글 조회]()|`GET`|`api/posts/{id}`|<br>
|[게시글 수정]()|`PATCH`|`api/posts/{id}`|<br>
|[게시글 삭제]()|`DELETE`|`api/posts/{id}`|<br>
|[게시글 비밀번호]()|`GET`|`api/posts/{id}`|<br>


<br><br>

## 🌱 Think About
1. 수정, 삭제 API의 request를 어떤 방식으로 사용하셨나요? (param, query, body)
  ```
  
  ```

2. 어떤 상황에 어떤 방식의 request를 써야하나요?
  ```
  
  ```

3. RESTful한 API를 설계했나요? 어떤 부분이 그런가요? 어떤 부분이 그렇지 않나요?
  ```
  
  ```

4. 적절한 관심사 분리를 적용하였나요? (Controller, Repository, Service)
  ```
  
  ```

5. 작성한 코드에서 빈(Bean)을 모두 찾아보세요!
  ```
  
  ```
6. API 명세서 작성 가이드라인을 검색하여 직접 작성한 명세서와 비교해보세요!
  ```
  
  ```

<br><br>

## Stack
- Java
- Spring Boot
- SQL
- AWS
