# 전체 게시글 목록 조회하기

### Method : **`GET`**

### URI : `/api/posts`

<br>

## Request

### Body

없음
<br>

## Response

| Name | Type | Description | Required |
|--|--|--|--|
| success | `Boolean` | 요청의 성공 여부 |  O |
| data | `List<PostSum>` | 전체 게시글 목록 | O |
| error | `String` | 에러에 관한 내용 반환 | X |

<br>

#### ***`List<PostSum>`***



| Name | Type | Description | Required|
|--|--|--|--|
| id | `Long` | 각 게시글이 갖는 고유의 ID값 | O |
| title | `String` | 게시글 제목 | O |
| author | `String` | 게시글 작성자 | O |
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
  "data": [
    {
      "id": 30,
      "title": "김영희의 TIL",
      "author": "김영희",
      "createdAt": "2022-08-18T08:02:15.948116"
    },
    {
      "id": 27,
      "title": "박철수의 TIL",
      "author": "박철수",
      "createdAt": "2022-08-18T07:51:25.247885"
    }
  ],
  "error": null
}
```

