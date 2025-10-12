export interface CommentResponse {
  id: number;
  message: string;
  user: {
    username: string;
  };
}
