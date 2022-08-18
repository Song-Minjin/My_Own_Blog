# 한 게시글 조회하기

### Method : **`GET`**

### URI : `/api/posts/{id}`

<br>

## Request

### Body

없음

<br>

## Response

| Name | Type | Description | Required |
|--|--|--|--|
| success | `Boolean` | 요청의 성공 여부 |  O |
| data | `PostDetail` | 게시글 객체 | O |
| error | `String` | 에러에 관한 내용 반환 | X |

<br>

#### ***`PostDetail`***



| Name | Type | Description | Required |
|--|--|--|--|
| id | `Long` | 각 게시글이 갖는 고유의 ID값 | O |
| title | `String` | 게시글 제목 | O |
| author | `String` | 게시글 작성자 | O |
| content | `String` | 게시글 내용 | O |
| createdAt | `LocalDateTime` | 게시글 작성 날짜 및 시간 | O |

<br>

## Sample

### Request
```json

```

### Response
```json
{
  "success": true,
  "data": {
    "id": 30,
    "content": "ㅎㅎ",
    "title": "김영희의 TIL",
    "author": "김영희",
    "createdAt": "2022-08-18T08:02:15.948116"
  },
  "error": null
}
```

