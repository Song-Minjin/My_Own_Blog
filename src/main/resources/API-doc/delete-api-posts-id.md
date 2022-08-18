# 게시글 삭제하기

### Method : **`DELETE`**

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
| Message | `String` | 사용자에게 보여줄 메시지 |  O |
| error | `String` | 에러에 관한 내용 반환 | X |

<br>

## Sample

### Request
```json

```

### Response
```json
{
  "success": true,
  "message": "정상적으로 17번 글이 삭제되었습니다.",
  "error": null
}
```

