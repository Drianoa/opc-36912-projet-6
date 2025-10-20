export interface PostResponse {
  id: number;
  title: string;
  content: string;
  topic: {
    name: string;
  };
  owner: {
    username: string;
  };
  createdAt: string;
}
