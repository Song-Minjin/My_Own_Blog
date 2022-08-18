# 나만의 블로그
> Spring Boot로 CRUD 구현하기 과제

<br>

## 🧾 과제 요구사항 리스트

<a href=""><img src="https://velog.velcdn.com/images/sw_smj/post/24ae0f2c-9d9b-4816-a676-c42fd7d2d591/image.png" align="center" width="700"></a>



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


<a href=""><img src="https://velog.velcdn.com/images/sw_smj/post/4fc901bc-a9a9-41b5-8040-a46a518d2124/image.png" align="center" width="250"></a>


### Table name : Post
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


| Function                                                                                                              | Method   | URL             |
|-----------------------------------------------------------------------------------------------------------------------|----------|------------------|
| [전체 게시글 목록 조회](https://github.com/Song-Minjin/My_Own_Blog/blob/master/src/main/resources/API-doc/get-api-posts.md)    | `GET`    | `api/posts`      |
| [게시글 작성](https://github.com/Song-Minjin/My_Own_Blog/blob/master/src/main/resources/API-doc/post-api-posts.md)         | `POST`   | `api/posts`      |
| [한 게시글 조회](https://github.com/Song-Minjin/My_Own_Blog/blob/master/src/main/resources/API-doc/get-api-posts-id.md)     | `GET`    | `api/posts/{id}` |
| [게시글 비밀번호 확인](https://github.com/Song-Minjin/My_Own_Blog/blob/master/src/main/resources/API-doc/post-api-posts-id.md) | `POST`   | `api/posts/{id}` |
| [게시글 수정](https://github.com/Song-Minjin/My_Own_Blog/blob/master/src/main/resources/API-doc/patch-api-posts-id.md)                                                                                                            | `PATCH`  | `api/posts/{id}` |
| [게시글 삭제](https://github.com/Song-Minjin/My_Own_Blog/blob/master/src/main/resources/API-doc/delete-api-posts-id.md)                                                                                                            | `DELETE` | `api/posts/{id}` |


<br><br>

# 🌱 Think About
<br>

## 1. 수정, 삭제 API의 request를 어떤 방식으로 사용하셨나요? (param, query, body)
<br>

- 수정 API : `PATCH`

## 1. Request

### Path Variable
**`posts/{id}`**

- end point를 설계하는 데에 있어서, Path Variable(=Path Parameter)만을 활용했다.

- Query문을 활용하지 않은 이유는, 게시글 수정이라는 기능에는 '데이터 식별' 기능만으로 충분했기 때문이다.

- 단, 나중에 기능을 추가할 때에는 검색을 하거나 특정 조건을 걸어 게시글을 조회할 경우에는 Query String을 쓰는 것이 합리적이겠다.


### Body

**Parameter**

| Name | Type | Description | Required|
|--|--|--|--|
| title | `String` | 게시글 제목 | O |
| author | `String` | 게시글 작성자 | O |
| content | `String` | 게시글 내용 | O |

`POST` 메소드와 거의 유사하지만, 비밀번호는 초기설정값을 유지하도록 설정해두었다. 따라서 Body에 보내는 내용에도 password가 빠져있다.


<br>

## 2. Response

| Name | Type | Description | Required |
|--|--|--|--|
| success | `Boolean` | 요청의 성공 여부 |  O |
| Message | `String` | 사용자에게 보여줄 메시지 |  O |
| error | `String` | 에러에 관한 내용 반환 | X |

<br><br>

## 삭제 API : `DETELE`

## 1. Request

### Header

- 나는 헤더에 데이터를 넣어 전송하지는 않았지만, 예를 들어 삭제를 요청한 유저의 권한 등을 확인하기 위해서는 헤더에 인증 정보를 넣어 요청을 하는 경우가 있다.


### Path Variable
**`posts/{id}`**

- end point를 설계하는 데에 있어서, Path Variable(=Path Parameter)만을 활용했다.

- Query문을 활용하지 않은 이유는, 게시글 삭제라는 기능에는 '데이터 식별' 기능만으로 충분했기 때문이다.


### Request Body

- 삭제 메소드에서 Body를 가질 수는 있다고는 한다. 그러나 사실상 Delete 메소드의 역할에 비추어 봐도 의미 없기 때문에 사용하지 않는 경우가 많다.

- 보통 요청에 필요한 정보는 Query String이나 Path Variable을 활용하여 데이터를 전달한다.

- 설정을 변경해서 Payload body를 파싱하는 방법 등이 있다고는 하지만, 가장 추천하지는 않는다고 한다.

<br>

## 2. Response
| Name | Type | Description | Required |
|--|--|--|--|
| success | `Boolean` | 요청의 성공 여부 |  O |
| Message | `String` | 사용자에게 보여줄 메시지 |  O |
| error | `String` | 에러에 관한 내용 반환 | X |

<br><br>

## 2. 어떤 상황에 어떤 방식의 request를 써야하나요?
  
> HTTP Request는 `Path Parameter`, `Query String`, `Body`로 구성된다. (줄여서 Param, Query, Body)

이때, Body는 POST, PATCH/PUT의 경우에 데이터를 담아보내고, 그 외 `GET`과 `DELETE`에서는 Body에 데이터를 담지 않는다고 생각하는 것이 편하다(`DELETE`는 사실상 Body를 가질 수는 있지만, 보통은 잘 안 씀).

그리고 나머지 Path Parameter와 Query String은 상황에 따라 선택하여 통신할 수 있다. 두 가지 다 데이터 중심으로 요청, 반환이 이루어진다.

## 1. Path parameter 이용하기

> Path Parameter = Path Variable (거의 혼용하여 사용)
   
- 필요한 상황과 내가 원하는 정보에 따라 URI를 다르게 요청하는 방식
- 데이터를 넘기는 방법 중 하나로, 경로를 변수로서 사용하는 방법
- 한 URI 내에서도 메소드별로 주고 받는 데이터의 Request body, Response body가 다르다. 따라서 같은 URI, 같은 path param을 쓰더라도 같은 API라는 보장은 절대 없음!

### 예시

- `GET /posts/{id}`  : 이러한 형식으로, {id} 부분에 해당 데이터의 ID를 넣는다

- `GET /posts` → 강의 전체 목록 조회 요청

- `GET /posts/1` → ID가 1번인 녀석 조회 요청

- `GET /posts/1/comments` → ID가 1번인 녀석의 댓글 조회 요청

- `POST /posts` → 강의 생성 요청

- `PUT /posts/3` → ID가 3번인 녀석 수정 요청

- `DELETE /posts/2` → ID 2번인 녀석 삭제 요청

<br>

## 2. Query string 이용하기

> URL의 끝에 ?와 함께 key-value값을 가지는 요청 파라미터

- URL의 뒤에 입력 데이터를 함께 제공하는 가장 단순한 데이터 전달 방법

- URL에 조회조건을 표시하기 때문에 특정 페이지를 링크하거나 북마크할 수 있음

- 클라이언트가 어떤 특정 리소스에 접근하고 싶어하는지에 대한 정보를 담음

### 사용 방법
- 정해진 엔드포인트 주소 이후 `?`를 쓰는 것으로 쿼리스트링이 시작
- `parameter = value` 형식으로 필요한 파라미터의 값을 적음
- 파라미터가 여러 개일 경우 &를 붙여 여러 개의 파라미터를 넘김
- `=`로 key와 value가 구분됨

### 예시
- `GET /posts?ordering=-id` → DB 내 데이터를 역순으로 호출하여 보여줌


- `GET /posts?offset=0&limit=100` → 0부터 100개까지 범위 지정 (pagination 등에 사용)

<br>

### ➡ Param vs Query 두 방식 비교

- **Path parameter** : 정제되지 않은 데이터를 호출해옴
👉🏻 resource 식별에 적절!

- **Query string** : 좀 더 복잡한 조건을 줘서 내가 원하는 정제된 결과물을 얻을 수 있음
👉🏻 filtering, sorting, searching에 적절!

<br><br>

## 3. RESTful한 API를 설계했나요? 어떤 부분이 그런가요? 어떤 부분이 그렇지 않나요?

그렇다고 생각한다. 우선, 제약 사항을 맞추기 위해 노력했고 REST한 결과물이 나왔다.

- 모든 자원은 URI로 명시되었으며,

- HTTP METHOD(`GET`, `POST`, `PATCH`,`DELETE`)를 통해 데이터를 주고받았다.

- 필요한 정보와 상태코드를 Client Server로 응답을 보냈다.<br>
<br>

### RESTful API 특징 충족 여부

**✅ Server-Client**<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;✔ 클라이언트-서버 및 리소스로 구성됨<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;✔ 요청이 HTTP를 통해 관리됨<br>


**✅ Stateless**<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;✔ 요청간에 클라이언트 정보가 저장되지 않음 (GET에는 Password를 담지 않도록 @GitIgnore 처리)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;✔ 요청이 HTTP를 통해 관리됨<br>

**✅ Cacheable**<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;✔ 클라이언트-서버 상호작용을 간소화함 (GET 메소드가 웹캐시 보관)<br>


**✅ Layered System**<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;✔ 요청된 정보를 검색할 때, 관련된 서버의 각 유형을 클라이언트가 볼 수 없는 계층 구조로 체계화함<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;✔ Repository, Service, Controller의 역할을 명확히 함<br>


**✅ Uniform Interface**<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;✔ 요청된 리소스가 식별 가능<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;✔ 요청된 리소스가 클라이언트에 전송된 표현과 분리됨<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ➡ 예를 들어, 아래 보이는 코드 중 `GET` 메소드를 통해 요청된 데이터는 data에 해당하는 부분만!<br>
  ```json
  {
      "success": true,
      "data": {
          "id": 7,
          "content": "오늘은 잘래",
          "title": "박철수의 TIL",
          "author": "박철수",
          "createdAt": "2022-08-17T15:47:44.723483"
      },
      "error": null
  }
  ```

<br><br><br>

## 4. 적절한 관심사 분리를 적용하였나요? (Controller, Repository, Service)

그렇다. 특히 입력(변수 선언), 처리(계산 처리), 출력(계산 결과)가 잘 분리되어 정리되도록 노력했다.

객체는 Entity 패키지에, DTO 및 Repository 역시 각각의 패키지에 정리했다. 특히, 강의 자료에서는 Controller에서 CRUD 중 `POST`, `GET`, `DELETE` 등의 작업은 바로 Repository에 대한 작업을 실행하는 반면, 나는 Repository와 소통해야 하는 부분들은 모두 Service에 넣어 계층화를 깔끔하게 하는 데에 집중했다.

또, JSON 파일로 출력을 할 때에도 Service 등에서 복잡하게 작업하지 않고, Response될 객체들을 따로 생성하여 작업하였다. 이는 계산 처리가 되는 부분(Service)과 입력(변수 선언, 아마 Entity 부분)이 잘 분리되었다고 볼 수 있겠다.

<br><br><br>

## 5. 작성한 코드에서 빈(Bean)을 모두 찾아보세요!

추후 추가하겠습니다.

<br><br><br>

## 6. API 명세서 작성 가이드라인을 검색하여 직접 작성한 명세서와 비교해보세요!

가이드라인의 조건과 유사하도록 작성하였다. 자세한 내용은 위의 API 명세에서 확인할 수 있다.

명확한 REST API가 되도록 했다.
