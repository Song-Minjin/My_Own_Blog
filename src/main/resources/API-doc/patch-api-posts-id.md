# 게시글 수정하기

### Method : **`PATCH`**

### URI : `/api/posts/{id}`

<br>

## Request

### Body

**Parameter**

| Name | Type | Description | Required|
|--|--|--|--|
| title | `String` | 게시글 제목 | O |
| author | `String` | 게시글 작성자 | O |
| content | `String` | 게시글 내용 | O |

<br>

## Response

| Name | Type | Description | Required |
|--|--|--|--|
| success | `Boolean` | 요청의 성공 여부 |  O |
| Message | `String` | 사용자에게 보여줄 메시지 |  O |
| error | `String` | 에러에 관한 내용 반환 | X |

<br>

## Sample

### Request
```json
{
    "title":"김영희의 TIL",
    "author":"김영희",
    "content":"ㅎㅎ"
}
```

### Response
```json
{
  "success": true,
  "message": "정상적으로 8번 글이 수정되었습니다.",
  "error": null
}
```

