-- =========================================================
-- Stable demo entity IDs (identical on every fresh DB install)
-- =========================================================
-- account:        00000001-0000-4000-8000-000000000001
-- app_user:       00000001-0000-4000-8000-000000000002
-- account_user:   00000001-0000-4000-8000-000000000003
-- agent (gpt):    00000001-0000-4000-8000-000000000011
-- agent (support):00000001-0000-4000-8000-000000000012
-- document (java):00000001-0000-4000-8000-000000000021
-- document (junior):00000001-0000-4000-8000-000000000022
-- document (fe):  00000001-0000-4000-8000-000000000023
-- document (devops):00000001-0000-4000-8000-000000000024
-- section (java): 00000001-0000-4000-8000-000000000031
-- section (junior):00000001-0000-4000-8000-000000000032
-- section (fe):   00000001-0000-4000-8000-000000000033
-- section (devops):00000001-0000-4000-8000-000000000034
-- thread (java):  00000001-0000-4000-8000-000000000041
-- thread (devops):00000001-0000-4000-8000-000000000042
-- message 1–4:    00000001-0000-4000-8000-000000000051 … 054

-- =========================================================
-- Account and user
-- =========================================================

INSERT INTO account (id, name, plan_tier, status)
SELECT '00000001-0000-4000-8000-000000000001', 'GenAI for Developers', 'demo', 'active'
WHERE NOT EXISTS (
    SELECT 1 FROM account WHERE id = '00000001-0000-4000-8000-000000000001'
);

INSERT INTO app_user (id, email, display_name)
SELECT '00000001-0000-4000-8000-000000000002', 'chris@mailinator.com', 'Chris'
WHERE NOT EXISTS (
    SELECT 1 FROM app_user WHERE id = '00000001-0000-4000-8000-000000000002'
);

INSERT INTO account_user (id, account_id, user_id, role, status)
SELECT
    '00000001-0000-4000-8000-000000000003',
    '00000001-0000-4000-8000-000000000001',
    '00000001-0000-4000-8000-000000000002',
    'owner',
    'active'
WHERE NOT EXISTS (
    SELECT 1 FROM account_user WHERE id = '00000001-0000-4000-8000-000000000003'
);

-- =========================================================
-- Agents
-- =========================================================

INSERT INTO agent (
    id, account_id, name, llm_model, embeddings_model, reranking_model,
    max_tokens, temperature, behavior, created_by, updated_by
)
SELECT
    '00000001-0000-4000-8000-000000000011',
    '00000001-0000-4000-8000-000000000001',
    'my-gpt-5.1-mini',
    'gpt-5.1-mini',
    'text-embedding-3-small',
    'colbert-latest',
    1024,
    0.30,
    'Respond concisely with actionable next steps.',
    '00000001-0000-4000-8000-000000000002',
    '00000001-0000-4000-8000-000000000002'
WHERE NOT EXISTS (
    SELECT 1 FROM agent WHERE id = '00000001-0000-4000-8000-000000000011'
);

INSERT INTO agent (
    id, account_id, name, llm_model, embeddings_model, reranking_model,
    max_tokens, temperature, behavior, created_by, updated_by
)
SELECT
    '00000001-0000-4000-8000-000000000012',
    '00000001-0000-4000-8000-000000000001',
    'support-pro',
    'gpt-4.1',
    'text-embedding-3-large',
    'bge-large',
    2048,
    0.20,
    'Sound professional and detail every diagnostic step.',
    '00000001-0000-4000-8000-000000000002',
    '00000001-0000-4000-8000-000000000002'
WHERE NOT EXISTS (
    SELECT 1 FROM agent WHERE id = '00000001-0000-4000-8000-000000000012'
);

-- =========================================================
-- Documents
-- =========================================================

INSERT INTO document (id, account_id, title, summary, body, created_by, status)
SELECT
    '00000001-0000-4000-8000-000000000021',
    '00000001-0000-4000-8000-000000000001',
    'Senior Java Developer',
    'Own enterprise-scale Spring microservices modernization.',
    '- Lead large-scale modernization projects
- Mentor engineers and ensure reliability KPIs
- Coordinate with product on roadmap delivery',
    '00000001-0000-4000-8000-000000000002',
    'active'
WHERE NOT EXISTS (
    SELECT 1 FROM document WHERE id = '00000001-0000-4000-8000-000000000021'
);

INSERT INTO document (id, account_id, title, summary, body, created_by, status)
SELECT
    '00000001-0000-4000-8000-000000000022',
    '00000001-0000-4000-8000-000000000001',
    'Junior Backend Developer',
    'Build internal APIs and automation tooling.',
    '- Maintain Node.js services and pipelines
- Ship integrations with finance tooling
- Collaborate with DevOps for smoother releases',
    '00000001-0000-4000-8000-000000000002',
    'active'
WHERE NOT EXISTS (
    SELECT 1 FROM document WHERE id = '00000001-0000-4000-8000-000000000022'
);

INSERT INTO document (id, account_id, title, summary, body, created_by, status)
SELECT
    '00000001-0000-4000-8000-000000000023',
    '00000001-0000-4000-8000-000000000001',
    'Frontend Developer',
    'Deliver Next.js experiences for AI document flows.',
    '- Build accessible UI for document comparisons
- Implement live previews for GenAI output
- Partner with design on rapid experiments',
    '00000001-0000-4000-8000-000000000002',
    'active'
WHERE NOT EXISTS (
    SELECT 1 FROM document WHERE id = '00000001-0000-4000-8000-000000000023'
);

INSERT INTO document (id, account_id, title, summary, body, created_by, status)
SELECT
    '00000001-0000-4000-8000-000000000024',
    '00000001-0000-4000-8000-000000000001',
    'DevOps Engineer',
    'Scale hybrid-cloud infra with IaC and observability.',
    '- Own IaC modules for AWS/GCP rollout
- Improve telemetry and incident response
- Champion developer productivity workflows',
    '00000001-0000-4000-8000-000000000002',
    'active'
WHERE NOT EXISTS (
    SELECT 1 FROM document WHERE id = '00000001-0000-4000-8000-000000000024'
);

-- =========================================================
-- Chat threads
-- =========================================================

INSERT INTO chat_thread (id, account_id, agent_id, title, status, created_by)
SELECT
    '00000001-0000-4000-8000-000000000041',
    '00000001-0000-4000-8000-000000000001',
    '00000001-0000-4000-8000-000000000011',
    'Senior Java discovery',
    'active',
    '00000001-0000-4000-8000-000000000002'
WHERE NOT EXISTS (
    SELECT 1 FROM chat_thread WHERE id = '00000001-0000-4000-8000-000000000041'
);

INSERT INTO chat_thread (id, account_id, agent_id, title, status, created_by)
SELECT
    '00000001-0000-4000-8000-000000000042',
    '00000001-0000-4000-8000-000000000001',
    '00000001-0000-4000-8000-000000000012',
    'DevOps hiring Q&A',
    'active',
    '00000001-0000-4000-8000-000000000002'
WHERE NOT EXISTS (
    SELECT 1 FROM chat_thread WHERE id = '00000001-0000-4000-8000-000000000042'
);

-- =========================================================
-- Chat messages
-- =========================================================

INSERT INTO chat_message (id, thread_id, account_id, agent_id, user_id, sender_type, content)
SELECT
    '00000001-0000-4000-8000-000000000051',
    '00000001-0000-4000-8000-000000000041',
    '00000001-0000-4000-8000-000000000001',
    '00000001-0000-4000-8000-000000000011',
    '00000001-0000-4000-8000-000000000002',
    'user',
    'Can you summarize the senior Java posting for stakeholder updates?'
WHERE NOT EXISTS (
    SELECT 1 FROM chat_message WHERE id = '00000001-0000-4000-8000-000000000051'
);

INSERT INTO chat_message (id, thread_id, account_id, agent_id, user_id, sender_type, content)
SELECT
    '00000001-0000-4000-8000-000000000052',
    '00000001-0000-4000-8000-000000000041',
    '00000001-0000-4000-8000-000000000001',
    '00000001-0000-4000-8000-000000000011',
    NULL,
    'agent',
    'The role focuses on modernizing Spring-based services and mentoring engineers.'
WHERE NOT EXISTS (
    SELECT 1 FROM chat_message WHERE id = '00000001-0000-4000-8000-000000000052'
);

INSERT INTO chat_message (id, thread_id, account_id, agent_id, user_id, sender_type, content)
SELECT
    '00000001-0000-4000-8000-000000000053',
    '00000001-0000-4000-8000-000000000042',
    '00000001-0000-4000-8000-000000000001',
    '00000001-0000-4000-8000-000000000012',
    '00000001-0000-4000-8000-000000000002',
    'user',
    'What are the key outcomes for the DevOps engineer?'
WHERE NOT EXISTS (
    SELECT 1 FROM chat_message WHERE id = '00000001-0000-4000-8000-000000000053'
);

INSERT INTO chat_message (id, thread_id, account_id, agent_id, user_id, sender_type, content)
SELECT
    '00000001-0000-4000-8000-000000000054',
    '00000001-0000-4000-8000-000000000042',
    '00000001-0000-4000-8000-000000000001',
    '00000001-0000-4000-8000-000000000012',
    NULL,
    'agent',
    'They will own IaC modules, telemetry improvements, and productivity tooling.'
WHERE NOT EXISTS (
    SELECT 1 FROM chat_message WHERE id = '00000001-0000-4000-8000-000000000054'
);
