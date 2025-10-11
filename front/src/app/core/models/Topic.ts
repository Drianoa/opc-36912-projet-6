export interface Topic {
  id: number;
  name: string;
  description: string;
}

export interface UserTopic extends Topic {
  subscribed: boolean;
}
