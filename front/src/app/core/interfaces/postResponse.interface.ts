export interface PostResponse {
  id: number;
  title: string;
  content: string;
  topicId: number;
  owner: {
    username: string;
  };
  createdAt: string;
}
